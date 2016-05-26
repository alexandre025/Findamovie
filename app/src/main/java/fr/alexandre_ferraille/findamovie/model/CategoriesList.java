package fr.alexandre_ferraille.findamovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexandre on 15/05/16.
 */
public class CategoriesList implements Parcelable {

    @JsonProperty("genres")
    private ArrayList<Category> categories;

    public CategoriesList() {
    }

    protected CategoriesList(Parcel in) {
        categories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<CategoriesList> CREATOR = new Creator<CategoriesList>() {
        @Override
        public CategoriesList createFromParcel(Parcel in) {
            return new CategoriesList(in);
        }

        @Override
        public CategoriesList[] newArray(int size) {
            return new CategoriesList[size];
        }
    };

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(categories);
    }
}
