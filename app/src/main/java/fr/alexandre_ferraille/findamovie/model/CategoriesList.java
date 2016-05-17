package fr.alexandre_ferraille.findamovie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandre on 15/05/16.
 */
public class CategoriesList implements Serializable {

    @JsonProperty("genres")
    private ArrayList<Category> categories;

    public CategoriesList() {
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
