package net.hetic.findamovie.model.managers;

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

    /**
     * Set DB with GreenDAO
     * @param context
     */
    public MyMoviesManager(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"db",null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * Close DB
     */
    public void close() {
        db.close();
    }

    /**
     * Add a movie into the DB
     * @param movie
     */
    public void addMovie(Movie movie) {
        MovieDao movieDao = daoSession.getMovieDao();
        movieDao.insert(movie);
    }

    /**
     * Get all movies in the DB, order by title
     * @return
     */
    public List getAllMovie() {
        MovieDao movieDao = daoSession.getMovieDao();
        List movies = movieDao.queryBuilder()
                .orderAsc(MovieDao.Properties.Title)
                .list();
        return movies;
    }

    /**
     * Delete a movie into the DB
     * @param movie
     */
    public void delete(Movie movie) {
        MovieDao movieDao = daoSession.getMovieDao();
        movieDao.delete(movie);
    }

    /**
     * Check if a movie is already in the DB
     * @param Id
     * @return
     */
    public Boolean isSaved(Long Id){
        MovieDao movieDao = daoSession.getMovieDao();
        List movies = movieDao.queryBuilder()
                .where(MovieDao.Properties.Id.eq(Id))
                .list();
        return movies.isEmpty();
    }

    /**
     * Get a specific movie by Id
     * @param Id
     * @return
     */
    public Movie getMovie(Long Id) {
        System.out.println(Id);
        MovieDao movieDao = daoSession.getMovieDao();
        List movies = movieDao.queryBuilder()
                .where(MovieDao.Properties.Id.eq(Id))
                .list();
        Movie movie = (Movie) movies.get(0);
        return movie;
    }

}
