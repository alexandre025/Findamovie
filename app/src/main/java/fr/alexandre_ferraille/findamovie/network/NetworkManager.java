package fr.alexandre_ferraille.findamovie.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.model.Category;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;

/**
 * Created by alexandre on 13/05/16.
 */
public class NetworkManager {

    /**
     * Return available categories
     * @param listener
     */
    public static void getCategories(final CategoriesListener listener) {
        String url = UrlBuilder.getCategoriesUrl();
        Log.i("GENRES URL", url);

        JacksonRequest<Category[]> request = new JacksonRequest<>(Request.Method.GET, url, new JacksonRequestListener<Category[]>() {
            @Override
            public void onResponse(Category[] response, int statusCode, VolleyError error) {
                if (error != null) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                } else {
                    if (response != null) {
                        if (listener != null) {
                            listener.onReceiveCategories(response);
                        }
                    }
                }
            }

            @Override
            public JavaType getReturnType() {
                return ArrayType.construct(SimpleType.construct(Category.class), null, null);
            }
        });

        MyApp.getInstance().getRequestQueue().add(request);
    }

    /**
     * Listener for Categories
     */
    public interface CategoriesListener {
        void onReceiveCategories(Category[] categories);

        void onFailed();
    }

    /**
     * Return movies requested by categories
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
