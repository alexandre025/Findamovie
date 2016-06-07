package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsTabOverviewFragment extends Fragment {


    private static String ARGUMENT_MOVIE = "argument_movie";
    private View rootView;
    private Movie movie;

    @BindView(R.id.movie_overview_textview)
    TextView movieOverviewTextview;

    @BindView(R.id.loader_view)
    RelativeLayout loaderView;

    public MovieDetailsTabOverviewFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsTabOverviewFragment newInstance(Movie selectedMovie) {
        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_MOVIE, selectedMovie);

        MovieDetailsTabOverviewFragment fragment = new MovieDetailsTabOverviewFragment();

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
        rootView = inflater.inflate(R.layout.fragment_movie_details_tab_overview, container, false);

        ButterKnife.bind(this, rootView);

        loaderView.setVisibility(View.GONE);

        movieOverviewTextview.setText(movie.getOverview());

        return rootView;
    }

}
