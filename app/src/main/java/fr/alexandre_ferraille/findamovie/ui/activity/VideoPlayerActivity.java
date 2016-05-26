package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Video;
import fr.alexandre_ferraille.findamovie.ui.fragment.YoutubeTrailerFragment;

public class VideoPlayerActivity extends AppCompatActivity {

    public static String ARGUMENT_VIDEO = "argument_video";
    private Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        video = intent.getParcelableExtra(ARGUMENT_VIDEO);

        switch (video.getSite()){
            case "YouTube":
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.main_container, YoutubeTrailerFragment.newInstance(video.getKey()))
                        .commit();
                break;
            default:
                finish();
                break;
        }

    }
}
