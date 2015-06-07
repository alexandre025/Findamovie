package net.hetic.findamovie;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.hetic.findamovie.adapters.CategoryAdapter;
import net.hetic.findamovie.model.Category;
import net.hetic.findamovie.network.NetworkAccess;

import java.util.ArrayList;


public class FindMovie extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView step1Comment;
    private Button step1Next;
    private CategoryAdapter adapter;
    ArrayList<String> selectedGenres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_movie);
        //getSupportActionBar().hide(); // REMOVE THIS LINE TO DISPLAY ACTION BAR

        step1Comment = (TextView) findViewById(R.id.step1_comment);
        Typeface font = Typeface.createFromAsset(getAssets(), "Lato-Regular.ttf");
        step1Comment.setTypeface(font);

        step1Next = (Button) findViewById(R.id.step1_next);

        ArrayList<Category> categories = new ArrayList<Category>();
        Category category = new Category("Action","28");
        categories.add(category);
        category = new Category("Aventure","12");
        categories.add(category);
        category = new Category("Animation","16");
        categories.add(category);
        category = new Category("Comédie","35");
        categories.add(category);
        category = new Category("Crime","80");
        categories.add(category);
        category = new Category("Documentaire","99");
        categories.add(category);
        category = new Category("Drame","18");
        categories.add(category);
        category = new Category("Etranger","10769");
        categories.add(category);
        category = new Category("Familial","10751");
        categories.add(category);
        category = new Category("Guerre","10752");
        categories.add(category);
        category = new Category("Histoire","36");
        categories.add(category);
        category = new Category("Horreur","27");
        categories.add(category);
        category = new Category("Musique","10402");
        categories.add(category);
        category = new Category("Mystère","9648");
        categories.add(category);
        category = new Category("Romance","10749");
        categories.add(category);
        category = new Category("Science-Fiction","878");
        categories.add(category);
        category = new Category("Téléfilm","10770");
        categories.add(category);
        category = new Category("Thriller","53");
        categories.add(category);
        category = new Category("Western","37");
        categories.add(category);

        adapter = new CategoryAdapter(this, categories);
        setListAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        step1Next.setOnClickListener(this);
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

    public void toggleGenres(View v){
        CheckBox mCheckBox = (CheckBox) v;
        LinearLayout mParent = (LinearLayout) mCheckBox.getParent();
        TextView mTextView = (TextView) mParent.getChildAt(1);
        if(mCheckBox.isChecked()){
            selectedGenres.add(mTextView.getText().toString());
        }
        else{
            selectedGenres.remove(mTextView.getText().toString());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == step1Next){
            String mApiRequest = "with_genres=";
            for (int i = 0; i < selectedGenres.size(); i++){
                if(i!=(selectedGenres.size()-1)) {
                    mApiRequest = mApiRequest + selectedGenres.get(i)+",";
                }
                else {
                    mApiRequest = mApiRequest + selectedGenres.get(i);
                }
            }
            NetworkAccess.requestMovies(mApiRequest);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
