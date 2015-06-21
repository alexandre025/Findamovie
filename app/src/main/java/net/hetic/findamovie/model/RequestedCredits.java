package net.hetic.findamovie.model;

import java.util.ArrayList;

/**
 * Created by alexandre on 21/06/15.
 */
public class RequestedCredits {
    ArrayList<Cast> cast;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}
