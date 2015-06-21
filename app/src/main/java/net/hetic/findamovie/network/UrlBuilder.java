package net.hetic.findamovie.network;

import net.hetic.findamovie.MyApp;

/**
 * Created by alexandre on 21/06/15.
 */
public class UrlBuilder {

    private static String apiKey = "?api_key=70890ed92e2d332f35ea0cd41086c921";
    private static String language = "&language="+ MyApp.getLanguage();

    /**
     * List genres basic url for TMDb
     * @return
     */
    public static String baseGenres(){
        String url = "http://api.themoviedb.org/3/genre/movie/list";
        return url+apiKey+language;
    }

    /**
     * Discover basic url for TMDb
     * @return
     */
    public static String baseDiscover(){
        String url = "http://api.themoviedb.org/3/discover/movie";
        return url+apiKey+language;
    }

    /**
     * Movie credits basic url for TMDb
     * @param id
     * @return
     */
    public static String baseCredits(Long id){
        String url = "http://api.themoviedb.org/3/movie/";
        return url+id+"/credits"+apiKey;
    }

    /**
     * Movie credits basic url for TMDb
     * @param id
     * @return
     */
    public static String baseImages(Long id){
        String url = "http://api.themoviedb.org/3/movie/";
        return url+id+"/images"+apiKey;
    }

    /**
     * 500px width images for TMDb
     * @return
     */
    public static String baseW500(String path){
        String url = "http://image.tmdb.org/t/p/w500";
        return url+path;
    }

}
