package fr.alexandre_ferraille.findamovie.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;

import fr.alexandre_ferraille.findamovie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int[] getSelectedGenres(){
        int[] genres = {28,12};
        return genres;
    }
}