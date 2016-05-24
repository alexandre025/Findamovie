package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsTabVideosFragment extends Fragment {

    private static String ARGUMENT_MOVIE = "argument_movie";
    private Movie movie;
    private View rootView;

    public MovieDetailsTabVideosFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsTabVideosFragment newInstance(Movie movie) {

        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_MOVIE, movie);

        MovieDetailsTabVideosFragment fragment = new MovieDetailsTabVideosFragment();
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
        rootView = inflater.inflate(R.layout.fragment_movie_details_tab_videos, container, false);



        return rootView;
    }

}
