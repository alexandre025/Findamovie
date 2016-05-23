package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Cast;

/**
 * Created by alexandre on 23/05/16.
 */
public class CastingAdapter extends BaseAdapter {

    private ArrayList<Cast> casting;
    private Context mContext;

    @Override
    public int getCount() {
        return casting.size();
    }

    @Override
    public Cast getItem(int position) {
        return casting.get(position);
    }

    @Override
    public long getItemId(int position) {
        return casting.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CastingViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.casting_list_item, null);
            holder = new CastingViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CastingViewHolder) convertView.getTag();
        }

        holder.setCasting(getItem(position));

        return convertView;
    }

    public CastingAdapter(Context context) {
        this.casting = new ArrayList<>();
        this.mContext = context;
    }

    public void refresh(ArrayList<Cast> casting){
        this.casting.clear();
        this.casting.addAll(casting);
        notifyDataSetChanged();
    }
}
