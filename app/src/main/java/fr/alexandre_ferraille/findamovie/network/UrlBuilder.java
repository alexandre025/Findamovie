package fr.alexandre_ferraille.findamovie.network;

import fr.alexandre_ferraille.findamovie.MyApp;

/**
 * Created by alexandre on 11/05/16.
 */
public class UrlBuilder {

    private static final String BASE_URL = "http://api.themoviedb.org";

    private static final String API_KEY = "?api_key=70890ed92e2d332f35ea0cd41086c921";

    private static final String LANGUAGE = String.format("&language=%s",MyApp.getLanguage());

    /**
     * Discover basic url for TMDb
     * @return
     */
    public static String baseDiscover(int page){
        return String.format("%s/3/discover/movie%s%s&page=%d",BASE_URL,API_KEY,LANGUAGE,page);
    }


}
