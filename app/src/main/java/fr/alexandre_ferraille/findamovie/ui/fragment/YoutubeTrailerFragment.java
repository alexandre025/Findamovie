package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeTrailerFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    private static final String ARGUMENT_YOUTUBE_CODE = "argument_youTube_code";
    private View rootView;
    private String youtubeCode;

    public YoutubeTrailerFragment() {
        // Required empty public constructor
    }

    public static YoutubeTrailerFragment newInstance(String code){
        Bundle args = new Bundle();

        args.putString(ARGUMENT_YOUTUBE_CODE,code);

        YoutubeTrailerFragment fragment = new YoutubeTrailerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_trailer, container, false);

        Bundle args = getArguments();

        youtubeCode = args.getString(ARGUMENT_YOUTUBE_CODE);

        YouTubePlayerSupportFragment youtubePlayerFragment = new YouTubePlayerSupportFragment();
        youtubePlayerFragment.initialize(MyApp.getYoutubeDevKey(), this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, youtubePlayerFragment)
                .commit();

        return rootView;

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.setFullscreen(true);
            youTubePlayer.cueVideo(youtubeCode);
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog(getActivity(),youTubeInitializationResult.hashCode()).show();
    }
}
