package net.hetic.findamovie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.hetic.findamovie.model.Movie;
import net.hetic.findamovie.model.greendao.DaoMaster;
import net.hetic.findamovie.model.greendao.DaoSession;
import net.hetic.findamovie.model.greendao.MovieDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 04/06/15.
 */
public class MyMoviesManager {

    private DaoSession daoSession;
    private SQLiteDatabase db;

    public MyMoviesManager(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"db",null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public void close() {
        db.close();
    }

    public void addMovie(Movie movie) {
        MovieDao movieDao = daoSession.getMovieDao();
        movieDao.insert(movie);
    }

    public ArrayList<Movie> getAllMovie() {
        MovieDao movieDao = daoSession.getMovieDao();
        ArrayList<Movie> movies = new ArrayList<>(movieDao.loadAll());
        return movies;
    }

    public void delete(Movie movie) {
        MovieDao movieDao = daoSession.getMovieDao();
        movieDao.delete(movie);
    }

    public Boolean isSaved(Long Id){
        MovieDao movieDao = daoSession.getMovieDao();
        List movies = movieDao.queryBuilder()
                .where(MovieDao.Properties.Id.eq(Id))
                .list();
        return movies.isEmpty();
    }

    public Movie getMovie(Long Id) {
        MovieDao movieDao = daoSession.getMovieDao();
        List movies = movieDao.queryBuilder()
                .where(MovieDao.Properties.Id.eq(Id))
                .list();
        Movie movie = (Movie) movies.get(0);
        return movie;
    }

}
