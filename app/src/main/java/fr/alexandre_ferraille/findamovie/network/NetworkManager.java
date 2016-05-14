package fr.alexandre_ferraille.findamovie.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import java.lang.reflect.Constructor;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.model.Genre;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;

/**
 * Created by alexandre on 13/05/16.
 */
public class NetworkManager {

    /**
     * Return available genres
     * @param listener
     */
    public static void getGenres(final GenresListener listener) {
        String url = UrlBuilder.getGenresUrl();
        Log.i("GENRES URL", url);

        JacksonRequest<Genre[]> request = new JacksonRequest<>(Request.Method.GET, url, new JacksonRequestListener<Genre[]>() {
            @Override
            public void onResponse(Genre[] response, int statusCode, VolleyError error) {
                if (error != null) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                } else {
                    if (response != null) {
                        if (listener != null) {
                            listener.onReceiveGenres(response);
                        }
                    }
                }
            }

            @Override
            public JavaType getReturnType() {
                return ArrayType.construct(SimpleType.construct(Genre.class), null, null);
            }
        });

        MyApp.getInstance().getRequestQueue().add(request);
    }

    /**
     * Listener for Genres
     */
    public interface GenresListener {
        void onReceiveGenres(Genre[] genres);

        void onFailed();
    }

    /**
     * Return movies requested by genres
     *
     * @param page
     * @param genres
     * @param listener
     */
    public static void getMoviesResult(int page, int[] genres, final MoviesResultListener listener) {
        String url = UrlBuilder.getDiscoverUrl(page, genres);
        Log.i("MOVIE URL", url);

        JacksonRequest<MoviesResult> request = new JacksonRequest<>(Request.Method.GET, url, new JacksonRequestListener<MoviesResult>() {
            @Override
            public void onResponse(MoviesResult response, int statusCode, VolleyError error) {
                if (error != null) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                } else {
                    if (response != null) {
                        if (listener != null) {
                            listener.onReceiveMoviesResult(response);
                        }
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

    /**
     * Listener for MoviesResult
     */
    public interface MoviesResultListener {
        void onReceiveMoviesResult(MoviesResult result);

        void onFailed();
    }

}
