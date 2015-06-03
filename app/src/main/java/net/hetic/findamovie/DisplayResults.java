package net.hetic.findamovie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.RequestedMovies;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class DisplayResults extends ActionBarActivity {


    TextView mMovieSummary;
    TextView mMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        mMovieSummary = (TextView) findViewById(R.id.movieSummary);
        mMovieTitle = (TextView) findViewById(R.id.movieTitle);

        Intent intent = getIntent();
        String jsonData = intent.getStringExtra("REQUESTED_MOVIES");
        RequestedMovies mRequestedMovies = null;
        try {
            mRequestedMovies = getResult(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<Movie> test = mRequestedMovies.getResults();

        mMovieSummary.setText(test.get(0).getOverview());
        mMovieTitle.setText(test.get(0).getTitle());


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
}
