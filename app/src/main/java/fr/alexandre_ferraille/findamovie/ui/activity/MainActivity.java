package fr.alexandre_ferraille.findamovie.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.ui.fragment.CategoriesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoriesFragment categoriesFragment = new CategoriesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, categoriesFragment)
                .commit();
    }

}
