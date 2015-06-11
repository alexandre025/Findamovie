package net.hetic.findamovie.model;

/**
 * Created by alexandre on 28/05/15.
 */
public class Category {

    private String name;
    private Integer id;

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringId(){
        return id.toString();
    }
}
