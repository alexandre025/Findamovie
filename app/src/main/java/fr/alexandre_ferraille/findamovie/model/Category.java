package fr.alexandre_ferraille.findamovie.model;

import java.io.Serializable;

/**
 * Created by alexandre on 14/05/16.
 */
public class Category implements Serializable {

    private int id;
    private String name;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
