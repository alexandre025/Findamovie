package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.CategoriesList;
import fr.alexandre_ferraille.findamovie.model.Category;
import fr.alexandre_ferraille.findamovie.ui.adpater.CategoriesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private CategoriesList availableCategories;

    public interface OnCategoriesValidatedListener {
        public void onCategoriesValidated(ArrayList<String> categories);
    }

    OnCategoriesValidatedListener listener;

    public static final String ARGUMENT_CATEGORIES = "argument_categories";

    private View rootView;

    CategoriesAdapter categoriesAdapter;

    @BindView(R.id.categories_listview)
    ListView categoriesListview;

    @BindView(R.id.categories_validate_button)
    Button categoriesValidateButton;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static  CategoriesFragment newInstance(CategoriesList categoriesList) {

        Bundle args = new Bundle();

        args.putParcelable(ARGUMENT_CATEGORIES,categoriesList);

        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        availableCategories = args.getParcelable(ARGUMENT_CATEGORIES);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        ButterKnife.bind(this, rootView);

        listener = (OnCategoriesValidatedListener) getActivity();

        categoriesAdapter = new CategoriesAdapter(getContext());

        categoriesListview.setAdapter(categoriesAdapter);

        categoriesAdapter.refresh(availableCategories.getCategories());

        categoriesValidateButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.categories_validate_button:
                ArrayList<String> categories = new ArrayList<>();
                for (Category category : categoriesAdapter.getCheckedList()) {
                    categories.add(String.valueOf(category.getId()));
                }

                listener.onCategoriesValidated(categories);

                break;

        }
    }
}
