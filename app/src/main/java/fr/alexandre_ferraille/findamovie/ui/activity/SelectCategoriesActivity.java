package fr.alexandre_ferraille.findamovie.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.CategoriesList;
import fr.alexandre_ferraille.findamovie.network.CategoryNetworkManager;
import fr.alexandre_ferraille.findamovie.ui.fragment.CategoriesFragment;

public class SelectCategoriesActivity extends NavigationDrawerParentActivity implements CategoriesFragment.CategoriesFragmentListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_categories);

        ButterKnife.bind(this);

        setNavigation();

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
                //NetworkManager.networkUnavailableAlertDialog();
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
