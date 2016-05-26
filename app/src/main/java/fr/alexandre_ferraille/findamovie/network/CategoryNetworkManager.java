package fr.alexandre_ferraille.findamovie.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.model.CategoriesList;

/**
 * Created by alexandre on 22/05/16.
 */
public class CategoryNetworkManager {

    /**
     * Return available categories
     * @param listener
     */
    public static void getCategoriesList(final CategoriesListListener listener) {
        String url = UrlBuilder.getCategoriesUrl();
        Log.i("GENRES URL", url);

        JacksonRequest<CategoriesList> request = new JacksonRequest<>(Request.Method.GET, url, new JacksonRequestListener<CategoriesList>() {
            @Override
            public void onResponse(CategoriesList response, int statusCode, VolleyError error) {
                if (error != null) {
                    if (listener != null) {
                        listener.onFailed();
                    }
                } else {
                    if (response != null) {
                        if (listener != null) {
                            listener.onReceiveCategoriesList(response);
                        }
                    }
                }
            }

            @Override
            public JavaType getReturnType() {
                return SimpleType.construct(CategoriesList.class);
            }
        });

        MyApp.getInstance().getRequestQueue().add(request);
    }

    /**
     * Listener for Categories
     */
    public interface CategoriesListListener {
        void onReceiveCategoriesList(CategoriesList categoriesList);

        void onFailed();
    }
}
