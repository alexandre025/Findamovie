package net.hetic.findamovie;

import android.app.Application;



/**
 * Created by alexandre on 30/05/15.
 */
public class MyApp extends Application {

    private static MyApp sharedInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.sharedInstance = this;

    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

}

