package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.ui.fragment.CategoriesFragment;
import fr.alexandre_ferraille.findamovie.ui.fragment.MoviePagerFragment;

public class MoviePagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pager);

        Intent intent = getIntent();
        ArrayList<String> categories = intent.getStringArrayListExtra(CategoriesFragment.ARGUMENT_CATEGORIES);

        MoviePagerFragment moviePagerFragment = MoviePagerFragment.newInstance(categories);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, moviePagerFragment)
                .commit();
    }
}
