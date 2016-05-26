package fr.alexandre_ferraille.findamovie.network;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.MyApp;

/**
 * Created by alexandre on 11/05/16.
 */
public class UrlBuilder {

    private static final String BASE_URL = "http://api.themoviedb.org";

    private static final String API_KEY = "?api_key=70890ed92e2d332f35ea0cd41086c921";

    private static final String LANGUAGE = String.format("&language=%s", MyApp.getLanguage());

    public static String getCategoriesUrl() {
        return String.format("%s/3/genre/movie/list%s%s", BASE_URL, API_KEY, LANGUAGE);
    }

    /**
     * Discover basic url for TMDb
     *
     * @return
     */
    public static String getDiscoverUrl(int page, ArrayList<String> categories) {

        String listCategories = "";
        for (int i = 0; i < categories.size(); i++) {
            if (i != (categories.size() - 1)) {
                listCategories += categories.get(i) + ",";
            } else {
                listCategories += categories.get(i);
            }
        }

        return String.format("%s/3/discover/movie%s%s&page=%d&with_genres=%s", BASE_URL, API_KEY, LANGUAGE, page, listCategories);
    }

    /**
     * Return GET URL for credits of a selected movie
     * @param movieId
     * @return
     */
    public static String getMovieCreditsUrl(int movieId) {
        return String.format("%s/3/movie/%d/credits%s%s", BASE_URL, movieId, API_KEY, LANGUAGE);
    }

    /**
     * Return GET URL for videos of a selected movie
     * @param movieId
     * @return
     */
    public static String getMovieVideosUrl(int movieId) {
        return String.format("%s/3/movie/%d/videos%s%s", BASE_URL, movieId, API_KEY, LANGUAGE);
    }


    public static String getImageW500Url() {
        return "http://image.tmdb.org/t/p/w500";
    }

}
