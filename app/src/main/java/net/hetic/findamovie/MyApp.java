package net.hetic.findamovie;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.hetic.findamovie.model.Movie;

import java.util.Locale;

/**
 * Created by alexandre on 30/05/15.
 */
public class MyApp extends Application {

    private static MyApp sharedInstance;
    private static Context mContext;
    private static MyMoviesManager manager;
    public static Movie MovieToDetails;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.sharedInstance = this;
        mContext = getApplicationContext();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Lato-Regular.ttf");

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .diskCacheFileCount(1000)
                .build();
        ImageLoader.getInstance().init(config);

        manager = new MyMoviesManager(mContext);

    }

    public static Context getContext(){
        return mContext;
    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

    public static MyMoviesManager getManager() {
        return manager;
    }

    public static String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        System.out.println(language);
        if(!language.equals("fr"))
            language = "en";
        return language;
    }

}

