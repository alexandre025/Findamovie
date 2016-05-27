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

        RealmResults<Movie> result = query.findAll();

        return result;
    }

    public static boolean isSaved(Movie movie){
        Realm realm = Realm.getDefaultInstance();

        RealmQuery<Movie> query = realm.where(Movie.class);
        query.equalTo("saved", true);
        query.equalTo("id", movie.getId());

        RealmResults<Movie> result = query.findAll();

        if(result.size() == 1){
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

}
