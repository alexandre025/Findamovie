package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.CategoriesList;
import fr.alexandre_ferraille.findamovie.network.CategoryNetworkManager;
import fr.alexandre_ferraille.findamovie.network.MovieNetworkManager;
import fr.alexandre_ferraille.findamovie.ui.fragment.CategoriesFragment;

public class MainActivity extends AppCompatActivity implements CategoriesFragment.CategoriesFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryNetworkManager.getCategoriesList(new CategoryNetworkManager.CategoriesListListener() {
            @Override
            public void onReceiveCategoriesList(CategoriesList categoriesList) {
                CategoriesFragment categoriesFragment = CategoriesFragment.newInstance(categoriesList);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.main_container, categoriesFragment)
                        .commit();
            }

            @Override
            public void onFailed() {

            }
        });

    }

    @Override
    public void onCategoriesValidated(ArrayList<String> categories) {
        Log.e("CATEGORIES",categories.toString());
        Intent intent = new Intent(MyApp.getContext(),MoviePagerActivity.class);
        intent.putStringArrayListExtra(MoviePagerActivity.ARGUMENT_CATEGORIES,categories);
        startActivity(intent);
    }
}
