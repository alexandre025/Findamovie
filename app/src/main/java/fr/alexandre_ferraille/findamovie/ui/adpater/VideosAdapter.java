package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Video;

/**
 * Created by alexandre on 24/05/16.
 */
public class VideosAdapter extends BaseAdapter {

    private ArrayList<Video> mVideos;
    private Context mContext;

    public VideosAdapter(Context context) {
        mVideos = new ArrayList<>();
        mContext = context;
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }

    @Override
    public Video getItem(int position) {
        return mVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideosViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_list_item, null);
            holder = new VideosViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideosViewHolder) convertView.getTag();
        }

        holder.setVideo(getItem(position));

        return convertView;
    }

    public void refresh(ArrayList<Video> videos){
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }
}
