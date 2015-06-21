package net.hetic.findamovie.model;

import java.util.ArrayList;

/**
 * Created by alexandre on 21/06/15.
 */
public class RequestedImages {
    private ArrayList<MovieImage> backdrops;

    public ArrayList<MovieImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<MovieImage> backdrops) {
        this.backdrops = backdrops;
    }
}
