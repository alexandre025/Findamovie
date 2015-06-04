package net.hetic.findamovie;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.hetic.findamovie.model.greendao.DaoMaster;
import net.hetic.findamovie.model.greendao.DaoSession;


/**
 * Created by alexandre on 30/05/15.
 */
public class MyApp extends Application {

    private static MyApp sharedInstance;
    private static Context mContext;
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.sharedInstance = this;
        mContext = getApplicationContext();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setupDatabase();

    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public static Context getContext(){
        return mContext;
    }

    public static MyApp getInstance() {
        return sharedInstance;
    }

}

