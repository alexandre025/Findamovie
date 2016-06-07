package fr.alexandre_ferraille.findamovie.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.MyApp;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;
import fr.alexandre_ferraille.findamovie.model.MoviesResult;
import fr.alexandre_ferraille.findamovie.network.SearchNetworkManager;
import fr.alexandre_ferraille.findamovie.ui.adpater.SearchMoviesAdapter;

public class SearchResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_movies_listview)
    ListView searchMoviesListview;

    private SearchMoviesAdapter searchMoviesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchMoviesListview.setOnItemClickListener(this);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);

        }
    }

    private void showResults(String query) {
        SearchNetworkManager.searchMovies(1, query, new SearchNetworkManager.searchMoviesListener() {
            @Override
            public void onSearchedMoviesResult(MoviesResult moviesResult) {
                searchMoviesAdapter = new SearchMoviesAdapter(MyApp.getContext());
                searchMoviesListview.setAdapter(searchMoviesAdapter);
                Log.e("MOVIES",moviesResult.getMovies().toString());
                searchMoviesAdapter.refresh(moviesResult.getMovies());
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searchview_menu_items, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(false);

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = searchMoviesAdapter.getItem(position);

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.ARGUMENT_MOVIE,movie);
        startActivity(intent);
    }
}
