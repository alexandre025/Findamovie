package net.hetic.findamovie.utils;

import net.hetic.findamovie.model.RequestedCategories;
import net.hetic.findamovie.model.RequestedCredits;
import net.hetic.findamovie.model.RequestedImages;
import net.hetic.findamovie.model.RequestedMovies;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by alexandre on 21/06/15.
 */
public class Mapper {

    /**
     * Map credits into object
     * @param json
     * @param credits
     * @return
     */
    public static RequestedCredits mapResult(String json, RequestedCredits credits){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedCredits mResult = null;
        try {
            mResult = mapper.readValue(json, RequestedCredits.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;
    }

    /**
     * Map movies into object
     * @param json
     * @param movies
     * @return
     */
    public static RequestedMovies mapResult(String json, RequestedMovies movies){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedMovies mResult = null;
        try {
            mResult = mapper.readValue(json, RequestedMovies.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;
    }

    /**
     * Map images into object
     * @param json
     * @param images
     * @return
     */
    public static RequestedImages mapResult(String json, RequestedImages images){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedImages mResult = null;
        try {
            mResult = mapper.readValue(json, RequestedImages.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;
    }

    /**
     * Map categories into object
     * @param json
     * @param categories
     * @return
     */
    public static RequestedCategories mapResult(String json, RequestedCategories categories){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        RequestedCategories mResult = null;
        try {
            mResult = mapper.readValue(json, RequestedCategories.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResult;
    }


}
