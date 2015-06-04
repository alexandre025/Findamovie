package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "greendao");
        schema.setDefaultJavaPackageDao("net.hetic.findamovie.model.greendao");
        Entity movie = schema.addEntity("Movie");
        movie.addBooleanProperty("adult");
        movie.addIdProperty();
        movie.addStringProperty("overview");
        movie.addStringProperty("original_title");
        movie.addStringProperty("release_date");
        movie.addStringProperty("poster_path");
        movie.addDoubleProperty("popularity");
        movie.addStringProperty("title");
        movie.addDoubleProperty("vote_average");
        movie.addIntProperty("vote_count");
        new DaoGenerator().generateAll(schema, args[0]);
    }
}
