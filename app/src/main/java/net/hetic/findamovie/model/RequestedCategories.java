package net.hetic.findamovie.model;

import java.util.ArrayList;

/**
 * Created by alexandre on 11/06/15.
 * Save TMDb genres request
 */
public class RequestedCategories {
    private ArrayList<Category> genres;

    public RequestedCategories() {

    }

    public ArrayList<Category> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Category> genres) {
        this.genres = genres;
    }
}
