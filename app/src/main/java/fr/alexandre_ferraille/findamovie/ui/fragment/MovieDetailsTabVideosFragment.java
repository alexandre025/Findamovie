package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.model.MovieVideosResult;
import fr.alexandre_ferraille.findamovie.network.MovieNetworkManager;
import fr.alexandre_ferraille.findamovie.ui.activity.VideoPlayerActivity;
import fr.alexandre_ferraille.findamovie.ui.adpater.VideosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsTabVideosFragment extends Fragment {

    private static String ARGUMENT_MOVIE = "argument_movie";
    private Movie movie;

    @BindView(R.id.videos_listview)
    ListView listView;

    @BindView(R.id.loader_view)
    RelativeLayout loaderView;

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

        ButterKnife.bind(this, rootView);

        MovieNetworkManager.getMovieVideos(movie.getId(), new MovieNetworkManager.MovieVideosListener() {
            @Override
            public void onReceivedMovieVideos(MovieVideosResult movieVideosResult) {
                loaderView.setVisibility(View.GONE);

                final VideosAdapter videosAdapter = new VideosAdapter(getContext());

                listView.setAdapter(videosAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MyApp.getContext(), VideoPlayerActivity.class);
                        intent.putExtra(VideoPlayerActivity.ARGUMENT_VIDEO,videosAdapter.getItem(position));
                        startActivity(intent);
                    }
                });

                movieVideosResult.getVideos();

                videosAdapter.refresh(movieVideosResult.getVideos());
            }

            @Override
            public void onFailed() {

            }
        });

        return rootView;
    }

}
