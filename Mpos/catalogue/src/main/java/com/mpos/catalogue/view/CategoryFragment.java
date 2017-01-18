package com.mpos.catalogue.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.mpos.catalogue.R;
import com.mpos.catalogue.adapters.CategoryRecyclerViewAdapter;


import com.mpos.catalogue.database.AndroidDatabaseManager;
import com.mpos.catalogue.database.CatalogueDatabaseUtil;
import com.mpos.catalogue.model.Category;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener {

    EditText categoryNameEditText;
    Button addCategoryButton;
    Button cancelCategoryButton;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        categoryNameEditText = (EditText) rootView.findViewById(R.id.categoryaddedittext);
        addCategoryButton = (Button) rootView.findViewById(R.id.categoryaddbutton);
        cancelCategoryButton = (Button) rootView.findViewById(R.id.categorycancelbutton);
        addCategoryButton.setOnClickListener(this);
        cancelCategoryButton.setOnClickListener(this);
        List<Category> rowListItem = getAllcategoryList();// TODO : replace with MposDatabaseUtil.getAllCategories();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);

        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.item_recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        SlideInUpAnimator animator=new SlideInUpAnimator();
        animator.setRemoveDuration(500);
        rView.setItemAnimator(animator);

        CategoryRecyclerViewAdapter rcAdapter = new CategoryRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    private List<Category> getAllcategoryList() {
        List<Category> categoriesList = new ArrayList<Category>();
        categoriesList.add(new Category("001", "Breakfast",R.drawable.ic_delete));
        categoriesList.add(new Category("002", "Lunch",R.drawable.ic_delete));
        categoriesList.add(new Category("003", "Dinner",R.drawable.ic_delete));
        categoriesList.add(new Category("004", "Evening Snacks",R.drawable.ic_delete));
        categoriesList.add(new Category("005", "Thali",R.drawable.ic_delete));

        return categoriesList;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.categoryaddbutton) {
            String categoryName = categoryNameEditText.getText().toString();
            if (null != categoryName) {
                CatalogueDatabaseUtil.insertCategory("001", categoryName);

            }
        } else if (view.getId() == R.id.categorycancelbutton) {
            categoryNameEditText.setText("");
            startActivity(new Intent(getActivity(), AndroidDatabaseManager.class));
        }
    }

    private void addCategory(){

    }
}
