package fr.alexandre_ferraille.findamovie.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;

/**
 * Created by alexandre on 03/06/16.
 */
public class SearchNetworkManager {

    /**
     *
     * @param query
     * @param listener
     */
    public static void searchMovies(int page, String query, final searchMoviesListener listener) {
        String url = UrlBuilder.getSearchMoviesUrl(page, query);
        Log.e("URL", url);

        JacksonRequest<MoviesResult> request = new JacksonRequest<MoviesResult>(Request.Method.GET, url, new JacksonRequestListener<MoviesResult>() {
            @Override
            public void onResponse(MoviesResult response, int statusCode, VolleyError error) {
                if (error != null) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                } else {
                    if (listener != null) {
                        listener.onSearchedMoviesResult(response);
                    }
                }
            }

            @Override
            public JavaType getReturnType() {
                return SimpleType.construct(MoviesResult.class);
            }
        });

        MyApp.getInstance().getRequestQueue().add(request);
    }

    public interface searchMoviesListener {
        public void onSearchedMoviesResult(MoviesResult moviesResult);

        public void onFailed();
    }

}
