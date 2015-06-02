package net.hetic.findamovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import net.hetic.findamovie.R;
import net.hetic.findamovie.model.Category;

import java.util.ArrayList;

/**
 * Created by alexandre on 30/05/15.
 */
public class CategoryAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Category> mCategory;

    public CategoryAdapter(Context context, ArrayList<Category> category){
        mCategory = category;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_list_item, null);
            holder = new ViewHolder();
            holder.categoryCheckBox = (CheckBox) convertView.findViewById(R.id.categoryName);
            holder.apiNameTextView = (TextView) convertView.findViewById(R.id.categoryApiName);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = mCategory.get(position);
        holder.categoryCheckBox.setText(category.getName());
        holder.apiNameTextView.setText(category.getApi_name());

        return convertView;
    }

    private static class ViewHolder {
        CheckBox categoryCheckBox;
        TextView apiNameTextView;
    }
}
