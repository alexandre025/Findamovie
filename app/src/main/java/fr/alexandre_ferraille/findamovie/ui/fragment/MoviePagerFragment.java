package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.ui.adpater.MoviePagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePagerFragment extends Fragment {


    private View rootView;
    private ViewPager viewPager;

    public MoviePagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_movie_pager, container, false);

        viewPager = (ViewPager)rootView.findViewById(R.id.fragment_movie_viewpager);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        final List<MoviePagerStepFragment> moviePagerStepFragments = new ArrayList<>();

        NetworkManager.getMoviesResult(1, new NetworkManager.MoviesResultListener(){
            @Override
            public void onReceiveMoviesResult(MoviesResult result) {
                for(int i=0;i<result.getMovies().size();i++) {
                    moviePagerStepFragments.add(MoviePagerStepFragment.newInstance(result.getMovies().get(i)));
                }
                MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getFragmentManager(),moviePagerStepFragments);
                viewPager.setAdapter(moviePagerAdapter);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
