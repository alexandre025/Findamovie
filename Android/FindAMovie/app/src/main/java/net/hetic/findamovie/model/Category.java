package net.hetic.findamovie.model;

/**
 * Created by alexandre on 28/05/15.
 */
public class Category {

    private String name;
    private String api_name;

    public Category(String name, String api_name) {
        super();
        this.name = name;
        this.api_name = api_name;
    }

    public String getName() {
        return name;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
