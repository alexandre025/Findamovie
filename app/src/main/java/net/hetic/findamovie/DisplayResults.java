package net.hetic.findamovie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    TextView mMovieSummary;
    TextView mMovieTitle;
    ImageView mMovieCover;
    Button mNext;
    Button mSave;
    ArrayList<Movie> mMovieList;
    String request;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        getSupportActionBar().hide();

        mMovieSummary = (TextView) findViewById(R.id.movieSummary);
        mMovieTitle = (TextView) findViewById(R.id.movieTitle);
        mMovieCover = (ImageView) findViewById(R.id.movieCover);
        mNext = (Button) findViewById(R.id.nextButton);
        mSave = (Button) findViewById(R.id.saveButton);

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
        displayMovie(mMovieList.get(0));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mNext.setOnClickListener(this);
        mSave.setOnClickListener(this);
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
        mMovieSummary.setText(mMovie.getOverview());
        mMovieTitle.setText(mMovie.getTitle());
        NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500"+mMovie.getPoster_path(), mMovieCover);
    }

    @Override
    public void onClick(View v) {

        if((v == mSave || v == mNext) && !mMovieList.isEmpty()){
            mMovieList.remove(0);
            if(!mMovieList.isEmpty()) {
                if(mMovieList.size()==5){
                    page++;
                    NetworkAccess.nextPage(request+"&page="+page);
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
                displayMovie(mMovieList.get(0));
            }
        }
    }
}
