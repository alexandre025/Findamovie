package net.hetic.findamovie;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by alexandre on 30/05/15.
 */
public class MyApp extends Application {

    private static MyApp sharedInstance;
    private static Context mContext;
    private static MyMoviesManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.sharedInstance = this;
        mContext = getApplicationContext();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Lato-Regular.ttf");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
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

}

