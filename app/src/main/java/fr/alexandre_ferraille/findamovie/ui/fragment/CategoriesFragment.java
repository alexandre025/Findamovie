package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.alexandre_ferraille.findamovie.R;
import fr.alexandre_ferraille.findamovie.model.CategoriesList;
import fr.alexandre_ferraille.findamovie.network.NetworkManager;
import fr.alexandre_ferraille.findamovie.ui.adpater.CategoriesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    private View rootView;

    @BindView(R.id.categories_listview)
    ListView categoriesListview;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        ButterKnife.bind(this, rootView);

        final CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getContext());

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

        return rootView;


    }

}
