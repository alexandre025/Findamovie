package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.realm.MovieRealmManager;
import fr.alexandre_ferraille.findamovie.ui.adpater.MovieDetailsTabAdapter;

public class MovieDetailsActivity extends NavigationDrawerParentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private MovieDetailsTabAdapter movieDetailsTabAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.tabs_layout)
    TabLayout tabsLayout;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container_viewpager)
    ViewPager viewPager;

    public static String ARGUMENT_MOVIE = "argument_movie";

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        setNavigation();

        Intent intent = getIntent();
        movie = intent.getParcelableExtra(ARGUMENT_MOVIE);

        getSupportActionBar().setTitle(movie.getTitle());

        Boolean saved = MovieRealmManager.isSaved(movie);
        Log.e("IS SAVED ?",saved.toString());

        movieDetailsTabAdapter = new MovieDetailsTabAdapter(getSupportFragmentManager(), movie);

        viewPager.setAdapter(movieDetailsTabAdapter);

        tabsLayout.setupWithViewPager(viewPager);

    }
}
