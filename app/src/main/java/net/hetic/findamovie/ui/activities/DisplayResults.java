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

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.RequestedMovies;
import net.hetic.findamovie.network.NetworkAccess;
import net.hetic.findamovie.utils.OnSwipeTouchListener;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadNextPage = true;

        Intent intent = getIntent();
        request = intent.getStringExtra("LAST_REQUEST");
        String jsonData = intent.getStringExtra("REQUESTED_MOVIES");
        RequestedMovies mRequestedMovies = null;
        try {
            mRequestedMovies = getResult(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        page = mRequestedMovies.getPage();
        total_pages = mRequestedMovies.getTotal_pages();
        mMovieList = mRequestedMovies.getResults();

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
     * Parse a json results to a RequestedMovies object
     * @param jsonData
     * @return
     * @throws JSONException
     */
    private static RequestedMovies getResult(String jsonData) throws JSONException {

        // We use Jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedMovies mResult = null;
        try {
            mResult = mapper.readValue(jsonData, RequestedMovies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;

    }

    /**
     * Display a movie
     * @param mMovie
     */
    private void displayMovie(Movie mMovie){
        // Set content
        mMovieCover.setImageResource(R.drawable.background);
        mMovieSummary.setText(mMovie.getOverview());
        mMovieTitle.setText(mMovie.getTitle());

        // Set movie cover
        NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500" + mMovie.getPoster_path(), mMovieCover);

        // Set initial position for ScrollView
        mScrollView.scrollTo(0, 0);
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

        // Remove displayed movie
        if(!mMovieList.isEmpty())


        // If there is a next movie
        if(!mMovieList.isEmpty()) {

            mMovieList.remove(0);

            if(loadNextPage && mMovieList.size()<30 && page <= total_pages){

                // Next page of results
                page++;

                // Call the api
                NetworkAccess.nextPage(request+"&page="+page);

                loadNextPage = false;
            }

            // We display next movie
            selectNextMovie();

        }
        // If we are at the end of the movie list
        else if(mMovieList.isEmpty() && page > total_pages){
            System.out.println("THE END");
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
        if(v == mDetails){
            // New intent
            Intent intent = new Intent(MyApp.getContext(), MovieDetails.class);
            // Pass movie object by MyApp which extend Activity
            MyApp.MovieToDetails = mMovieList.get(0);
            // Start new activity
            startActivity(intent);
        }
    }

    class NextPageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String jsonData = intent.getStringExtra(NetworkAccess.NEXT_PAGE_EXTRA);
            RequestedMovies mRequestedMovies = null;
            try {
                // Jackson mapper transform json to RequestedMovies object
                mRequestedMovies = getResult(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jsonData);

            Boolean wasEmpty = mMovieList.isEmpty();

            // Append new movies at the end of the movie list
            while(!mRequestedMovies.getResults().isEmpty()) {

                mMovieList.add(mRequestedMovies.getResults().get(0));
                mRequestedMovies.getResults().remove(0);
            }

            loadNextPage = true;

            if(wasEmpty)
                selectNextMovie();

        }
    }
}
