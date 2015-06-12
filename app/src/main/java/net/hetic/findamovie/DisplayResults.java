package net.hetic.findamovie;

import android.content.Intent;
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

import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.RequestedMovies;
import net.hetic.findamovie.network.NetworkAccess;

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
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            displayMovie(mMovieList.get(0));

            View myView = this.getWindow().getDecorView();
            myView.setOnTouchListener(new OnSwipeTouchListener(MyApp.getContext()) {
                @Override
                public void onSwipeLeft() {
                    mMovieList.remove(0);
                    if(!mMovieList.isEmpty()) {
                        if(mMovieList.size()==5){
                            page++;
                            NetworkAccess.nextPage(request+"&page="+page);
                        }
                        displayMovie(mMovieList.get(0));
                    }
                    else {
                        String jsonData = NetworkAccess.nextPage("none");
                        RequestedMovies mRequestedMovies = null;
                        try {
                            mRequestedMovies = getResult(jsonData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mMovieList = mRequestedMovies.getResults();
                        displayMovie(mMovieList.get(0));
                    }
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

    private static RequestedMovies getResult(String jsonData) throws JSONException {

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

    private void displayMovie(Movie mMovie){
        mMovieCover.setImageResource(R.drawable.background);
        mMovieSummary.setText(mMovie.getOverview());
        mMovieTitle.setText(mMovie.getTitle());
        NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500" + mMovie.getPoster_path(), mMovieCover);
        mScrollView.scrollTo(0,0);
    }

    @Override
    public void onClick(View v) {

        if((v == mSave || v == mNext) && !mMovieList.isEmpty()){

            // PREVENT USERS FROM QUICK OVERSPEED-AND-CRASH
            mSave.setOnClickListener(null);
            mNext.setOnClickListener(null);

            Movie toSave = mMovieList.get(0);
            mMovieList.remove(0);
            if(!mMovieList.isEmpty()) {
                if(mMovieList.size()==5){
                    page++;
                    NetworkAccess.nextPage(request+"&page="+page);
                }
                if(v == mSave && MyApp.getManager().isSaved(toSave.getId())) {
                    MyApp.getManager().addMovie(toSave);
                }
                displayMovie(mMovieList.get(0));
            }
            else{
                String jsonData = NetworkAccess.nextPage("none");
                RequestedMovies mRequestedMovies = null;
                try {
                    mRequestedMovies = getResult(jsonData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mMovieList = mRequestedMovies.getResults();

                // ICI AJOUTER FIN DE LISTE GESTION

                displayMovie(mMovieList.get(0));

            }
            mSave.setOnClickListener(this);
            mNext.setOnClickListener(this);
        }

        if(v == mDetails){
            Intent intent = new Intent(MyApp.getContext(), MovieDetails.class);
            MyApp.MovieToDetails = mMovieList.get(0);
            startActivity(intent);
        }
    }
}
