package net.hetic.findamovie;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.hetic.findamovie.adapters.CategoryAdapter;
import net.hetic.findamovie.model.Category;

import java.util.ArrayList;


public class FindMovie extends ListActivity {

    private TextView step1Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movie);
        //getSupportActionBar().hide(); // REMOVE THIS LINE TO DISPLAY ACTION BAR

        step1Comment = (TextView) findViewById(R.id.step1_comment);
        Typeface font = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        step1Comment.setTypeface(font);

        ArrayList<Category> categories = new ArrayList<Category>();
        Category category = new Category("Horreur","horror");
        categories.add(category);
        category = new Category("Romance","romance");
        categories.add(category);
        category = new Category("Action","action");
        categories.add(category);
        category = new Category("Thriller","thriller");
        categories.add(category);
        category = new Category("Aventure","adventure");
        categories.add(category);
        category = new Category("Western","western");
        categories.add(category);
        category = new Category("Crime","crime");
        categories.add(category);
        category = new Category("Drame","drama");
        categories.add(category);
        category = new Category("Histoire","history");
        categories.add(category);
        category = new Category("Musique","music");
        categories.add(category);
        category = new Category("Animation","animate");
        categories.add(category);
        category = new Category("Comédie","comedie");
        categories.add(category);
        category = new Category("Documentaire","docu");
        categories.add(category);
        category = new Category("Familial","Guerre");
        categories.add(category);
        category = new Category("Mystère","mystery");
        categories.add(category);
        category = new Category("Science-Fiction","scify");
        categories.add(category);
        category = new Category("Téléfilm","tv");
        categories.add(category);
        category = new Category("Etranger","foreigner");
        categories.add(category);

        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
