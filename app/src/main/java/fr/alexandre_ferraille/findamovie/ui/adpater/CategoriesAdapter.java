package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Arrays;

import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Category;

/**
 * Created by alexandre on 14/05/16.
 */
public class CategoriesAdapter extends BaseAdapter {

    private final ArrayList<Category> categories;
    private final Context mContext;
    private ArrayList<String> checkedList;

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CategoriesViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_list_item, null);
            holder = new CategoriesViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (CategoriesViewHolder) convertView.getTag();
        }

        holder.getCategoryCheckbox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && !checkedList.contains(String.valueOf(position))){
                    checkedList.add(String.valueOf(position));
                }
                else if(!isChecked && checkedList.contains(String.valueOf(position))){
                    checkedList.remove(String.valueOf(position));
                }
            }
        });

        Category category = categories.get(position);

        holder.setCategory(category,checkedList.contains(String.valueOf(position)));

        return convertView;
    }

    public CategoriesAdapter(Context context) {
        categories = new ArrayList<>();
        checkedList = new ArrayList<>();
        mContext = context;
    }

    public void refresh(ArrayList<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }
}
