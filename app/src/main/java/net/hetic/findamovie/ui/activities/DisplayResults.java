package net.hetic.findamovie.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.RequestedMovies;
import net.hetic.findamovie.network.NetworkAccess;
import net.hetic.findamovie.network.UrlBuilder;
import net.hetic.findamovie.utils.Mapper;
import net.hetic.findamovie.utils.OnSwipeTouchListener;

import java.util.ArrayList;


public class DisplayResults extends ActionBarActivity implements View.OnClickListener {

    private TextView mMovieSummary;
    private TextView mMovieTitle;
    private ImageView mMovieCover;
    private ImageButton mNext;
    private ImageButton mSave;
    private Button mDetails;
    private ArrayList<Movie> mMovieList;
    private ScrollView mScrollView;
    private String request;
    private int page;
    private int total_pages;
    private Boolean loadNextPage;
    private NextPageReceiver receiver;
    private ArrayList<Movie> mRestartList;
    private Boolean mFirstRoll;
    private Boolean mSmthToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadNextPage = true;

        Intent intent = getIntent();
        request = intent.getStringExtra("LAST_REQUEST");
        String jsonData = intent.getStringExtra("REQUESTED_MOVIES");
        RequestedMovies mRequestedMovies = null;
        mRequestedMovies = Mapper.mapResult(jsonData, mRequestedMovies);

        page = mRequestedMovies.getPage();
        total_pages = mRequestedMovies.getTotal_pages();
        mMovieList = mRequestedMovies.getResults();
        mRestartList = (ArrayList<Movie>)mRequestedMovies.getResults().clone();
        mFirstRoll = true;
        mSmthToDisplay = true;

        if(!mMovieList.isEmpty()) {
            setContentView(R.layout.activity_display_results);
            getSupportActionBar().hide();

            mMovieSummary = (TextView) findViewById(R.id.movieSummary);
            mMovieTitle = (TextView) findViewById(R.id.movieTitle);
            mMovieCover = (ImageView) findViewById(R.id.movieCover);
            mNext = (ImageButton) findViewById(R.id.nextButton);
            mSave = (ImageButton) findViewById(R.id.saveButton);
            mScrollView = (ScrollView) findViewById(R.id.contentView);
            mDetails = (Button) findViewById(R.id.detailsButton);

            selectNextMovie();

            View myView = this.getWindow().getDecorView();
            myView.setOnTouchListener(new OnSwipeTouchListener(MyApp.getContext()) {
                @Override
                public void onSwipeLeft() {
                    moviesListLoading(false);
                }
            });

        }
        else {
            setContentView(R.layout.activity_display_no_results);
            getSupportActionBar().hide();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!mMovieList.isEmpty()) {
            mNext.setOnClickListener(this);
            mSave.setOnClickListener(this);
            mDetails.setOnClickListener(this);

        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        receiver = new NextPageReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(NetworkAccess.NEXT_PAGE));
    }

    @Override
    protected void onPause(){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        overridePendingTransition(R.anim.transition_fadein, R.anim.transition_fadeout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Display a movie
     * @param mMovie
     */
    private void displayMovie(Movie mMovie){
        // Set content
        mMovieCover.setImageResource(R.drawable.background);

        String summary = mMovie.getOverview();
        if(summary != null && summary.length()>300)
            summary = summary.substring(0,270)+" [...]";
        mMovieSummary.setText(summary);
        mMovieTitle.setText(mMovie.getTitle());

        // Set movie cover
        NetworkAccess.downloadImage(UrlBuilder.baseW500(mMovie.getPoster_path()), mMovieCover);

        // Set initial position for ScrollView
        mScrollView.scrollTo(0, 0);

        mSmthToDisplay = true;
    }

    /**
     * Select and display the next movie
     * @param needToSave
     */
    private void moviesListLoading(Boolean needToSave){

        // If user save the movie
        if(needToSave && MyApp.getManager().isSaved(mMovieList.get(0).getId())) {
            // Movie goes to GreenDAO
            MyApp.getManager().addMovie(mMovieList.get(0));
        }

        // If there is a next movie
        if(!mMovieList.isEmpty()) {

            mMovieList.remove(0);

            // If there's no next page loading enqueued, there are other pages of results and this pages were not previously loaded
            if(loadNextPage && mMovieList.size()<30 && page <= total_pages && mFirstRoll){

                // Next page of results
                page++;

                // Call the api
                NetworkAccess.nextPage(request+"&page="+page);

                loadNextPage = false;
            }

            // We display next movie
            selectNextMovie();

        }
        // If we are at the end of the movie list we reload the list from our cache "mRestartList"
        else if(mMovieList.isEmpty() && page >= total_pages && mSmthToDisplay){
            mMovieList = new ArrayList<>();
            mMovieList = (ArrayList<Movie>) mRestartList.clone();
            System.out.println(mMovieList.size());
            mFirstRoll = false;

            // Probably nothing to show, the case where users saved the entire list
            mSmthToDisplay = false;
            System.out.println(mSmthToDisplay);

            selectNextMovie();
        }
        // There's nothing to display, end of the activity
        else if(!mSmthToDisplay){
            Toast.makeText(this, this.getString(R.string.AlreadySavedAllItems), Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    /**
     * Select the next movie which is unsaved
     */
    private void selectNextMovie(){
        if(!mMovieList.isEmpty() && MyApp.getManager().isSaved(mMovieList.get(0).getId()))
            displayMovie(mMovieList.get(0));
        else
            moviesListLoading(false);
    }

    @Override
    public void onClick(View v) {

        if((v == mSave || v == mNext) && !mMovieList.isEmpty()){

            // PREVENT USERS FROM QUICK OVERSPEED-AND-CRASH
            mSave.setOnClickListener(null);
            mNext.setOnClickListener(null);

            if(v == mSave)
                moviesListLoading(true);
            else if(v == mNext)
                moviesListLoading(false);

            // Allow user to switch again
            mSave.setOnClickListener(this);
            mNext.setOnClickListener(this);
        }

        // User require more details for a movie
        if(v == mDetails && !mMovieList.isEmpty()){

            // New intent
            Intent intent = new Intent(MyApp.getContext(), MovieDetails.class);
            intent.putExtra(MovieDetails.MOVIE_TO_DETAILS,mMovieList.get(0));

            // Start new activity
            startActivity(intent);
        }
    }

    class NextPageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String jsonData = intent.getStringExtra(NetworkAccess.NEXT_PAGE_EXTRA);
            RequestedMovies mRequestedMovies = null;
            mRequestedMovies = Mapper.mapResult(jsonData,mRequestedMovies);

            Boolean wasEmpty = mMovieList.isEmpty();

            // Append new movies at the end of the movie list
            while(!mRequestedMovies.getResults().isEmpty()) {

                mMovieList.add(mRequestedMovies.getResults().get(0));
                mRestartList.add(mRequestedMovies.getResults().get(0).clone());
                mRequestedMovies.getResults().remove(0);
            }

            loadNextPage = true;

            if(wasEmpty)
                selectNextMovie();

        }
    }
}
