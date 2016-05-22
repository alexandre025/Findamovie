package fr.alexandre_ferraille.findamovie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexandre on 23/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cast {

    private int id;

    private String character, name;

    @JsonProperty("profile_path")
    private String profilePath;

    public Cast() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
