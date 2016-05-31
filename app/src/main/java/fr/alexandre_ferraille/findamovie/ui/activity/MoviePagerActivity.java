package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.ui.fragment.MoviePagerFragment;
import fr.alexandre_ferraille.findamovie.ui.fragment.MoviePagerStepFragment;

public class MoviePagerActivity extends NavigationDrawerParentActivity implements MoviePagerStepFragment.MoviePagerStepListener {

    public static String ARGUMENT_CATEGORIES = "argument_categories";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pager);

        ButterKnife.bind(this);

        setNavigation();

        Intent intent = getIntent();
        ArrayList<String> categories = intent.getStringArrayListExtra(ARGUMENT_CATEGORIES);

        MoviePagerFragment moviePagerFragment = MoviePagerFragment.newInstance(categories);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, moviePagerFragment)
                .commit();
    }

    @Override
    public void onMovieDetailsRequired(Movie movie) {
        Intent intent = new Intent(MyApp.getContext(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.ARGUMENT_MOVIE,movie);
        startActivity(intent);
    }

}
