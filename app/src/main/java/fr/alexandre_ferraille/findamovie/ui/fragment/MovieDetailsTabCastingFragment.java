package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.model.MovieCredits;
import fr.alexandre_ferraille.findamovie.network.MovieNetworkManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsTabCastingFragment extends Fragment {


    private static String ARGUMENT_MOVIE = "argument_movie";
    private View rootView;
    private Movie movie;

    public MovieDetailsTabCastingFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsTabCastingFragment newInstance(Movie selectedMovie) {

        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_MOVIE, selectedMovie);

        MovieDetailsTabCastingFragment fragment = new MovieDetailsTabCastingFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        movie = args.getParcelable(ARGUMENT_MOVIE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_details_tab_casting, container, false);

        ButterKnife.bind(this, rootView);

        MovieNetworkManager.getMovieCredits(movie.getId(), new MovieNetworkManager.MovieCreditsListener() {
            @Override
            public void onReceivedMovieCredits(MovieCredits movieCredits) {
                Log.e("RESULT","Received");
            }

            @Override
            public void onFailed() {

            }
        });

        return rootView;
    }

}
