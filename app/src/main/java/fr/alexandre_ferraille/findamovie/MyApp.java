package fr.alexandre_ferraille.findamovie;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Locale;

import fr.alexandre_ferraille.findamovie.network.LruBitmapCache;

/**
 * Created by alexandre on 11/05/16.
 */
public class MyApp extends Application {

    private static MyApp instance;
    private static Context context;

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private static final String YOUTUBE_DEV_KEY = "AIzaSyBx72EijX5WmAV6e0yq4rNoSH71RgqCGm0";

    @Override
    public void onCreate() {
        super.onCreate();

        MyApp.instance = this;

        MyApp.context = getApplicationContext();

        requestQueue = Volley.newRequestQueue(this);

        LruBitmapCache cache = new LruBitmapCache(8 * 1024 * 1024);
        imageLoader = new ImageLoader(requestQueue, cache);
    }

    public static MyApp getInstance() { return instance; }

    public static Context getContext() { return context; }

    public RequestQueue getRequestQueue() { return requestQueue; }

    public ImageLoader getImageLoader() { return imageLoader; }

    public static String getYoutubeDevKey() {
        return YOUTUBE_DEV_KEY;
    }

    public static String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (!language.equals("fr"))
            language = "en";
        return language;
    }

}
