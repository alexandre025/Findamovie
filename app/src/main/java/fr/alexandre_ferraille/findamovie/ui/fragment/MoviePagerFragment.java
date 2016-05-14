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
import fr.alexandre_ferraille.findamovie.model.MoviesResult;
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.ui.activity.MainActivity;
import fr.alexandre_ferraille.findamovie.ui.adpater.MoviePagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePagerFragment extends Fragment {


    private View rootView;
    private ViewPager viewPager;
    private int currentPage, maxPage;
    private int[] genres;

    public MoviePagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_movie_pager, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.fragment_movie_viewpager);

        MainActivity activity = (MainActivity) getActivity();
        genres = activity.getSelectedGenres();

        loadMovies();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadMovies() {
        final List<MoviePagerStepFragment> moviePagerStepFragments = new ArrayList<>();

        NetworkManager.getMoviesResult(1,genres, new NetworkManager.MoviesResultListener() {
            @Override
            public void onReceiveMoviesResult(MoviesResult result) {
                moviePagerStepFragments.addAll(getMoviePagerStepFragments(result));
                final MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getFragmentManager(), moviePagerStepFragments);
                viewPager.setAdapter(moviePagerAdapter);
                moviePagerStepFragments.clear();

                currentPage = result.getPage();
                maxPage = result.getTotal_pages();

                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (currentPage < maxPage && position % 5 == 0) {
                            currentPage++;
                            NetworkManager.getMoviesResult(currentPage,genres, new NetworkManager.MoviesResultListener() {
                                @Override
                                public void onReceiveMoviesResult(MoviesResult result) {
                                    moviePagerStepFragments.addAll(getMoviePagerStepFragments(result));
                                    moviePagerAdapter.add(moviePagerStepFragments);
                                    moviePagerStepFragments.clear();
                                }

                                @Override
                                public void onFailed() {

                                }
                            });
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private List<MoviePagerStepFragment> getMoviePagerStepFragments(MoviesResult result) {
        List<MoviePagerStepFragment> moviePagerStepFragments = new ArrayList<>();
        for (int i = 0; i < result.getMovies().size(); i++) {
            moviePagerStepFragments.add(MoviePagerStepFragment.newInstance(result.getMovies().get(i)));
        }
        return moviePagerStepFragments;
    }
}
