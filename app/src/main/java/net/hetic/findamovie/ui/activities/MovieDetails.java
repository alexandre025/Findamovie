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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Cast;
import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.MovieImage;
import net.hetic.findamovie.model.RequestedCredits;
import net.hetic.findamovie.model.RequestedImages;
import net.hetic.findamovie.network.NetworkAccess;
import net.hetic.findamovie.network.NetworkAccess_MovieDetails;
import net.hetic.findamovie.network.UrlBuilder;
import net.hetic.findamovie.utils.Mapper;
import net.hetic.findamovie.utils.OnSwipeTouchListener;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class MovieDetails extends ActionBarActivity {

    private static TextView mMovieTitle;
    private static TextView mMovieReleaseDate;
    private static TextView mMovieSummary;
    private static TextView mMovieCredits;
    private static ImageView mMovieCover;
    private static Movie mMovie;
    private static LinearLayout mImagesList;
    private static ScrollView mScrollView;
    private DetailsReceiver receiver;

    public static final String MOVIE_TO_DETAILS = "MOVIE_TO_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details); 

        overridePendingTransition(R.anim.transition_fadein, R.anim.transition_fadeout);

        getSupportActionBar().hide();

        Intent intent = getIntent();

        // Get current movie object
        mMovie = intent.getExtras().getParcelable(MOVIE_TO_DETAILS);

        // Request more details for a single movie
        NetworkAccess_MovieDetails.requestMovieCredits(mMovie.getId());
        NetworkAccess_MovieDetails.requestMovieImages(mMovie.getId());

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
        NetworkAccess.downloadImage(UrlBuilder.baseW500(mMovie.getPoster_path()), mMovieCover);

        // Get image slider container
        mImagesList = (LinearLayout) findViewById(R.id.imagesList);

        // Set return back gesture
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mScrollView.setOnTouchListener(new OnSwipeTouchListener(MyApp.getContext()) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });

        // Get credits view
        mMovieCredits = (TextView) findViewById(R.id.movieCredits);

    }

    @Override
    protected void onResume(){
        super.onResume();
        receiver = new DetailsReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(NetworkAccess_MovieDetails.DETAILS));
    }

    @Override
    protected void onPause(){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
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

    class DetailsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Bundle bundle = intent.getExtras();

            if(bundle != null){
                if(bundle.containsKey(NetworkAccess_MovieDetails.CREDITS_EXTRA)){
                    String jsonData = bundle.getString(NetworkAccess_MovieDetails.CREDITS_EXTRA,"null");
                    RequestedCredits mRequestedCredits = null;
                    mRequestedCredits = Mapper.mapResult(jsonData, mRequestedCredits);

                    ArrayList<Cast> cast = mRequestedCredits.getCast();
                    if(!cast.isEmpty()) {
                        int i = 0;
                        String castText = null;
                        while(!cast.isEmpty() && i<6) {
                            if(i==0)
                                castText = cast.get(0).getName()+", ";
                            else
                                castText = castText+cast.get(0).getName()+", ";
                            cast.remove(0);
                            i++;
                        }
                        mMovieCredits.setText(castText);
                    }

                }

                // Set images in the slider
                if(bundle.containsKey(NetworkAccess_MovieDetails.IMAGES_EXTRA)){
                    String jsonData = bundle.getString(NetworkAccess_MovieDetails.IMAGES_EXTRA,"null");
                    RequestedImages mRequestedImages = null;
                    mRequestedImages = Mapper.mapResult(jsonData, mRequestedImages);

                    ArrayList<MovieImage> movieImages = mRequestedImages.getBackdrops();
                    if(!movieImages.isEmpty()) {
                        ImageView imageView;
                        do {
                            imageView = new ImageView(context);
                            mImagesList.addView(imageView);
                            NetworkAccess.downloadImage(UrlBuilder.baseW500(movieImages.get(0).getFile_path()), imageView);
                            movieImages.remove(0);
                        } while (!movieImages.isEmpty());
                    }
                }
            }
        }
    }
}
