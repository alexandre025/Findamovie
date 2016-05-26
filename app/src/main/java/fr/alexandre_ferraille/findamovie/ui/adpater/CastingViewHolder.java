package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Cast;
import fr.alexandre_ferraille.findamovie.network.UrlBuilder;

/**
 * Created by alexandre on 23/05/16.
 */
public class CastingViewHolder {

    @BindView(R.id.cast_image_imageview)
    NetworkImageView castImageImageview;

    @BindView(R.id.cast_name_textview)
    TextView castNameTextview;

    @BindView(R.id.cast_character_textview)
    TextView getCastCharacterTextview;

    public CastingViewHolder(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    public void setCasting(Cast cast) {
        ImageLoader imageLoader = MyApp.getInstance().getImageLoader();

        castImageImageview.setImageUrl(UrlBuilder.getImageW500Url() + cast.getProfilePath(), imageLoader);
        castNameTextview.setText(cast.getName());
        getCastCharacterTextview.setText(cast.getCharacter());
    }
}
