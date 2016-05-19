package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.ui.adpater.MoviePagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePagerFragment extends Fragment {


    private static final String ARGUMENT_CATEGORIES = "argument_categories";
    private View rootView;

    @BindView((R.id.fragment_movie_viewpager))
    ViewPager viewPager;

    private int currentPage, maxPage;
    private ArrayList<String> categories;
    private boolean isRestored = false;

    public MoviePagerFragment() {
        // Required empty public constructor
    }

    public static MoviePagerFragment newInstance(ArrayList<String> categories) {
        Bundle args = new Bundle();

        args.putStringArrayList(ARGUMENT_CATEGORIES, categories);

        MoviePagerFragment moviePagerFragment = new MoviePagerFragment();
        moviePagerFragment.setArguments(args);

        return moviePagerFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_movie_pager, container, false);

        ButterKnife.bind(this, rootView);

        Log.e("RESTORED?", String.valueOf(isRestored));

        if (!isRestored) {
            loadMovies();
        }

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categories = new ArrayList<>();

        Bundle args = getArguments();

        categories = args.getStringArrayList(ARGUMENT_CATEGORIES);
    }

    private void loadMovies() {
        isRestored = true;

        final List<MoviePagerStepFragment> moviePagerStepFragments = new ArrayList<>();

        NetworkManager.getMoviesResult(1, categories, new NetworkManager.MoviesResultListener() {
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
                            NetworkManager.getMoviesResult(currentPage, categories, new NetworkManager.MoviesResultListener() {
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
