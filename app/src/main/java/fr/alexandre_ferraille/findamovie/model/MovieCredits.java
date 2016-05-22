package fr.alexandre_ferraille.findamovie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by alexandre on 23/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieCredits {

    private ArrayList<Cast> cast;

    private ArrayList<Crew> crew;

    private int id;

    public MovieCredits() {
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
