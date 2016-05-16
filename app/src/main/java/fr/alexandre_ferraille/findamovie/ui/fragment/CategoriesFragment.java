package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.ui.activity.MoviePagerActivity;
import fr.alexandre_ferraille.findamovie.ui.adpater.CategoriesAdapter;
import fr.alexandre_ferraille.findamovie.ui.adpater.MoviePagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        ButterKnife.bind(this, rootView);

        categoriesAdapter = new CategoriesAdapter(getContext());

        categoriesListview.setAdapter(categoriesAdapter);

        NetworkManager.getCategoriesList(new NetworkManager.CategoriesListListener() {
            @Override
            public void onReceiveCategoriesList(CategoriesList categoriesList) {
                categoriesAdapter.refresh(categoriesList.getCategories());
            }

            @Override
            public void onFailed() {

            }
        });

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

                Intent intent = new Intent(getActivity(), MoviePagerActivity.class);
                intent.putStringArrayListExtra(ARGUMENT_CATEGORIES,categories);
                startActivity(intent);

                //MoviePagerFragment moviePagerFragment = MoviePagerFragment.newInstance(categories);

                //getFragmentManager().beginTransaction().replace(R.id.main_container, moviePagerFragment).addToBackStack("CategoriesFragment").commit();
                break;

        }
    }
}
