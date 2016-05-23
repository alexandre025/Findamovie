package fr.alexandre_ferraille.findamovie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by alexandre on 23/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieVideosResult {

    private int id;
    private ArrayList<Video> videos;

    public MovieVideosResult() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
}
