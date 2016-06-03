package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Movie;

/**
 * Created by alexandre on 03/06/16.
 */
public class SearchMoviesAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private Context mContext;

    public SearchMoviesAdapter(Context context) {
        movies = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchMoviesViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_movie_list_item, null);
            holder = new SearchMoviesViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SearchMoviesViewHolder) convertView.getTag();
        }

        holder.setMovie(getItem(position));

        return convertView;
    }

    public void refresh(ArrayList<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }
}

