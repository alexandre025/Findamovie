package net.hetic.findamovie;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.network.NetworkAccess;


public class MyMovieView extends ActionBarActivity implements View.OnClickListener {

    private TextView mMovieSummary;
    private TextView mMovieTitle;
    private ImageView mMovieCover;
    private Button mDeleteButton;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movie_view);

        getSupportActionBar().hide();

        mMovieSummary = (TextView) findViewById(R.id.movieSummary);
        mMovieTitle = (TextView) findViewById(R.id.movieTitle);
        mMovieCover = (ImageView) findViewById(R.id.movieCover);
        mDeleteButton = (Button) findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        Long Id = intent.getLongExtra("THE_MOVIE", 0);
        mMovie = MyApp.getManager().getMovie(Id);
        displayMovie(mMovie);

    }

    @Override
    public void onStart() {
        super.onStart();
        mDeleteButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_movie_view, menu);
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

    private void displayMovie(Movie movie){
        mMovieCover.setImageResource(R.drawable.background);
        mMovieSummary.setText(movie.getOverview());
        mMovieTitle.setText(movie.getTitle());
        NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500" + movie.getPoster_path(), mMovieCover);
    }

    @Override
    public void onClick(View v) {
        if(v == mDeleteButton) {
            System.out.println("DELETE");
            MyApp.getManager().delete(mMovie);
            finish();
        }
    }
}
