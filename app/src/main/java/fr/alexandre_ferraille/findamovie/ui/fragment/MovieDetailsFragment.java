package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment {


    private static final String ARGUMENT_MOVIE = "argument_movie";
    private View rootView;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_MOVIE, movie);

        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);

        return movieDetailsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        Bundle args = getArguments();
        Movie movie = (Movie) args.getSerializable(ARGUMENT_MOVIE);

        return rootView;
    }

}
