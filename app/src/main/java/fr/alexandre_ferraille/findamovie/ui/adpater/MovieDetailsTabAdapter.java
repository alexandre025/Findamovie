package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.ui.fragment.MovieDetailsTabCastingFragment;
import fr.alexandre_ferraille.findamovie.ui.fragment.MovieDetailsTabOverviewFragment;
import fr.alexandre_ferraille.findamovie.ui.fragment.MovieDetailsTabVideosFragment;


/**
 * Created by alexandre on 18/05/16.
 */
public class MovieDetailsTabAdapter extends FragmentPagerAdapter {

    private Movie selectedMovie;

    public MovieDetailsTabAdapter(FragmentManager fm, Movie movie) {
        super(fm);
        selectedMovie = movie;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment;

        switch (position){
            case 0:
                return MovieDetailsTabOverviewFragment.newInstance(selectedMovie);
            case 1:
                return MovieDetailsTabCastingFragment.newInstance(selectedMovie);
            case 2:
                return MovieDetailsTabVideosFragment.newInstance(selectedMovie);
            case 3:
                return MovieDetailsTabOverviewFragment.newInstance(selectedMovie);
            default:
                return MovieDetailsTabOverviewFragment.newInstance(selectedMovie);
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Résumé";
            case 1:
                return "Casting";
            case 2:
                return "Vidéos";
            case 3:
                return "PHotos";
        }
        return null;
    }
}
