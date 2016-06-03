package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.network.UrlBuilder;

/**
 * Created by alexandre on 03/06/16.
 */
public class SearchMoviesViewHolder {

    @BindView(R.id.movie_title_textview)
    TextView movieTitleTextview;

    @BindView(R.id.movie_cover_imageview)
    NetworkImageView movieCoverImageview;

    public SearchMoviesViewHolder(View itemView) {

        ButterKnife.bind(this, itemView);

    }

    public void setMovie(Movie movie){
        ImageLoader imageLoader = MyApp.getInstance().getImageLoader();

        movieCoverImageview.setImageUrl(UrlBuilder.getImageW500Url() + movie.getPosterPath(), imageLoader);
        movieTitleTextview.setText(movie.getTitle());
    }
}
