package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.alexandre_ferraille.findamovie.ui.fragment.MoviePagerStepFragment;

/**
 * Created by alexandre on 13/05/16.
 */
public class MoviePagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<MoviePagerStepFragment> listStepFragment;

    public MoviePagerAdapter(FragmentManager fm, List<MoviePagerStepFragment> fragments) {
        super(fm);

        listStepFragment = new ArrayList<>(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return listStepFragment.get(position);
    }

    @Override
    public int getCount() {
        return listStepFragment.size();
    }
}
