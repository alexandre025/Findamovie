package fr.alexandre_ferraille.findamovie.realm;

import fr.alexandre_ferraille.findamovie.model.Movie;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by alexandre on 27/05/16.
 */
public class MovieRealmManager {

    public static RealmResults<Movie> getViewedMovies(){
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Movie> query = realm.where(Movie.class);
        query.equalTo("viewed", true);

        RealmResults<Movie> results = query.findAll();

        return results;
    }

    public static RealmResults<Movie> getSavedMovies(){
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Movie> query = realm.where(Movie.class);
        query.equalTo("viewed", true);

        RealmResults<Movie> results = query.findAll();

        return results;
    }

    public static boolean isSaved(Movie movie){
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Movie> query = realm.where(Movie.class);
        query.equalTo("saved", true);
        query.equalTo("id", movie.getId());

        Movie result = query.findFirst();

        if(result != null){
            return true;
        }
        return false;
    }

    public static boolean isViewed(Movie movie){
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Movie> query = realm.where(Movie.class);
        query.equalTo("viewed", true);
        query.equalTo("id", movie.getId());

        RealmResults<Movie> result = query.findAll();

        if(result.size() == 1){
            return true;
        }
        return false;
    }

    public static boolean setSaved(Movie movie) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        if(!isSaved(movie)){
            movie.setSaved(true);
            realm.copyToRealm(movie);

        } else {
            Movie savedMovie = realm.where(Movie.class).equalTo("id",movie.getId()).findFirst();
            savedMovie.setSaved(true);
        }
        realm.commitTransaction();
        return true;
    }

}
