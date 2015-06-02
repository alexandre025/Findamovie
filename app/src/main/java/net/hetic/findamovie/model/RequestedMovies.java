package net.hetic.findamovie.model;

import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class RequestedMovies {
    private int page;
    private ArrayList<Movie> results;
    private int total_pages;
    private int total_results;

    public RequestedMovies() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
