package fr.alexandre_ferraille.findamovie.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.alexandre_ferraille.findamovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    private View rootView;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        return rootView;


    }

}
