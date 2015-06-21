package net.hetic.findamovie.ui.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.hetic.findamovie.R;
import net.hetic.findamovie.ui.adapters.CategoryAdapter;
import net.hetic.findamovie.model.Category;
import net.hetic.findamovie.model.RequestedCategories;
import net.hetic.findamovie.network.NetworkAccess;
import net.hetic.findamovie.utils.Mapper;

import java.util.ArrayList;


public class SelectGenres extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView step1Comment;
    private Button step1Next;
    private CategoryAdapter adapter;
    ArrayList<String> selectedGenres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_genres);
        //getSupportActionBar().hide(); // REMOVE THIS LINE TO DISPLAY ACTION BAR

        step1Next = (Button) findViewById(R.id.step1_next);

        Intent intent = getIntent();
        String jsonData = intent.getStringExtra("REQUESTED_GENRES");

        RequestedCategories mRequestedCategories = null;
        ArrayList<Category> categories = Mapper.mapResult(jsonData,mRequestedCategories).getGenres();

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

    /**
     * Save checkbox status in an ArrayList for future request
     * @param v
     */
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
                // Build the genres request
                if(i!=(selectedGenres.size()-1)) {
                    mApiRequest = mApiRequest + selectedGenres.get(i)+",";
                }
                else {
                    mApiRequest = mApiRequest + selectedGenres.get(i);
                }
            }
            // Request the movies
            NetworkAccess.requestMovies(mApiRequest);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
