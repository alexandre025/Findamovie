package net.hetic.findamovie.ui.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.hetic.findamovie.MyApp;
import net.hetic.findamovie.R;
import net.hetic.findamovie.ui.adapters.MovieAdapter;
import net.hetic.findamovie.model.Movie;

import java.util.ArrayList;


public class MyMovies extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MovieAdapter adapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies);

        adapter = new MovieAdapter(this, (ArrayList) MyApp.getManager().getAllMovie());
        setListAdapter(adapter);

        mListView = getListView();
    }

    @Override
    public void onStart(){
        super.onStart();
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();

        // Refresh listView, user may have delete a movie
        adapter = new MovieAdapter(this, (ArrayList) MyApp.getManager().getAllMovie());
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_movies, menu);
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(MyApp.getContext(), MyMovieView.class);
        Movie movie = (Movie) adapter.getItem(position);
        Long Id = movie.getId();
        intent.putExtra("THE_MOVIE", Id);
        startActivity(intent);
    }
}
