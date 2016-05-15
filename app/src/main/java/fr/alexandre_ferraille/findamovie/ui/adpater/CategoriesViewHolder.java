package fr.alexandre_ferraille.findamovie.ui.adpater;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.Category;

/**
 * Created by alexandre on 14/05/16.
 */
public class CategoriesViewHolder {

    @BindView(R.id.category_api_id_textview)
    TextView categoryApiIdTextview;

    @BindView(R.id.category_checkbox)
    CheckBox categoryCheckbox;

    public CategoriesViewHolder(View itemView) {

        ButterKnife.bind(this, itemView);

    }

    public void setCategory(Category category, Boolean isSelected) {
        categoryApiIdTextview.setText(String.valueOf(category.getId()));
        categoryCheckbox.setText(category.getName());
        categoryCheckbox.setChecked(isSelected);
    }

    public CheckBox getCategoryCheckbox() {
        return categoryCheckbox;
    }
}
