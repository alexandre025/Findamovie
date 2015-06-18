package net.hetic.findamovie.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Movie;

import java.util.ArrayList;

/**
 * Created by alexandre on 06/06/15.
 */
public class MovieAdapter extends BaseAdapter {

        public ArrayList<Movie> mMovieList;
        private Context mContext;

    public MovieAdapter(Context context, ArrayList<Movie> movieList){
        mContext = context;
        mMovieList = movieList;
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, null);
            holder = new ViewHolder();
            holder.mMovieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
            //holder.mMovieCover = (ImageView) convertView.findViewById(R.id.movieCover);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        Movie movie = mMovieList.get(position);
        holder.mMovieTitle.setText(movie.getTitle());
        //NetworkAccess.downloadImage("http://image.tmdb.org/t/p/w500" + movie.getPoster_path(), holder.mMovieCover);

        return convertView;
    }

    private static class ViewHolder {
        TextView mMovieTitle;
        //ImageView mMovieCover;
    }
}