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

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.sharedInstance = this;
        mContext = getApplicationContext();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

    }

    public static Context getContext(){
        return mContext;
    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

}

