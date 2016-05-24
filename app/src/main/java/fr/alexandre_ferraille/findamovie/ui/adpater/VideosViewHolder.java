package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Video;

/**
 * Created by alexandre on 24/05/16.
 */
public class VideosViewHolder {

    @BindView(R.id.video_title_textview)
    TextView videoTitleTextview;

    public VideosViewHolder(View itemView) {

        ButterKnife.bind(this, itemView);

    }

    public void setVideo(Video video) {
        videoTitleTextview.setText(video.getName());
    }


}
