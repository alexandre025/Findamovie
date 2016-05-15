package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.network.UrlBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePagerStepFragment extends Fragment {


    private static final String MOVIE_ARGUMENT = "movie_argument";
    private View rootView;

    @BindView(R.id.movie_title_textview)
    TextView movieTitleTextview;

    @BindView(R.id.movie_cover_imageview)
    NetworkImageView movieCoverImageview;

    @BindView(R.id.movie_overview_textview)
    TextView movieOverviewTextview;

    public MoviePagerStepFragment() {
        // Required empty public constructor
    }

    public static MoviePagerStepFragment newInstance(Movie movie) {

        Bundle args = new Bundle();

        args.putSerializable(MOVIE_ARGUMENT, movie);

        MoviePagerStepFragment fragment = new MoviePagerStepFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_movie_pager_step, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            Movie movie = (Movie) args.getSerializable(MOVIE_ARGUMENT);

            ImageLoader imageLoader = MyApp.getInstance().getImageLoader();
            movieCoverImageview.setImageUrl(UrlBuilder.getImageW500Url() + movie.getPosterPath(), imageLoader);

            movieTitleTextview.setText(movie.getTitle());

            movieOverviewTextview.setText(movie.getOverview());
        }
    }
}