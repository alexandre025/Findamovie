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
 * Created by alexandre on 13/05/16.
 */
public class NetworkManager {

    public interface MoviesResultListener {
        void onReceiveMoviesResult(MoviesResult result);
        void onFailed();
    }

    public static void getMoviesResult(int page, final MoviesResultListener listener) {
        String url = UrlBuilder.baseDiscover(page);
        Log.i("MOVIE URL",url);

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

}
