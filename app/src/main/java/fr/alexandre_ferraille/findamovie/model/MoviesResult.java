package fr.alexandre_ferraille.findamovie.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by alexandre on 13/05/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesResult {

    @JsonProperty("results")
    private ArrayList<Movie> Movies;
    private int page, total_pages, totals_results;

    public MoviesResult() {
    }

    public ArrayList<Movie> getMovies() {
        return Movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        Movies = movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotals_results() {
        return totals_results;
    }

    public void setTotals_results(int totals_results) {
        this.totals_results = totals_results;
    }
}
