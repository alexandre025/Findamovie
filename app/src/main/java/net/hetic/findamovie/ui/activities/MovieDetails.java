package net.hetic.findamovie.ui.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.network.NetworkAccess;


public class MovieDetails extends ActionBarActivity {

    private static TextView mMovieTitle;
    private static TextView mMovieReleaseDate;
    private static TextView mMovieSummary;
    private static ImageView mMovieCover;
    private static Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        overridePendingTransition(R.anim.transition_fadein, R.anim.transition_fadeout);

        getSupportActionBar().hide();

        // Get current movie object
        mMovie = MyApp.MovieToDetails;

        // Set release date
        mMovieReleaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        if(mMovie.getRelease_date() != null)
            mMovieReleaseDate.setText(MyApp.getContext().getString(R.string.releaseDate)+" "+mMovie.getRelease_date());
        else
            mMovieReleaseDate.setText(MyApp.getContext().getString(R.string.releaseDate)+" "+MyApp.getContext().getString(R.string.undefined));

        // Set title
        mMovieTitle = (TextView) findViewById(R.id.movieTitle);
        mMovieTitle.setText(mMovie.getTitle().toString());

        // Set summary
        mMovieSummary = (TextView) findViewById(R.id.movieSummary);
        if(mMovie.getOverview() != null)
            mMovieSummary.setText(mMovie.getOverview().toString());

        // Set cover
        mMovieCover = (ImageView) findViewById(R.id.movieCover);
        NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500" + mMovie.getPoster_path(), mMovieCover);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
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
}
