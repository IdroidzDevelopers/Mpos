package com.mpos.catalogue.view;


import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;


import com.hyperbound.network.util.VolleyUtil;
import com.mpos.catalogue.R;
import com.mpos.catalogue.adapters.CategoryRecyclerViewAdapter;


import com.mpos.catalogue.database.AndroidDatabaseManager;
import com.mpos.catalogue.loader.CategoryLoader;
import com.mpos.catalogue.model.Category;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<Category>> {

    EditText categoryNameEditText;
    Button addCategoryButton;
    Button cancelCategoryButton;
    List<Category> rowListItem;
    CategoryRecyclerViewAdapter rcAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        categoryNameEditText = (EditText) rootView.findViewById(R.id.categoryaddedittext);
        addCategoryButton = (Button) rootView.findViewById(R.id.categoryaddbutton);
        //addCategoryButton.setTextColor(Color.WHITE);
        cancelCategoryButton = (Button) rootView.findViewById(R.id.categorycancelbutton);
        // cancelCategoryButton.setTextColor(Color.WHITE);
        addCategoryButton.setOnClickListener(this);
        cancelCategoryButton.setOnClickListener(this);
        rowListItem = new ArrayList<Category>();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);

        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.item_recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        SlideInUpAnimator animator = new SlideInUpAnimator();
        animator.setRemoveDuration(1000);
        animator.setAddDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        rView.setItemAnimator(animator);
        getLoaderManager().initLoader(1, null, this).forceLoad();
        rcAdapter = new CategoryRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.categoryaddbutton) {
            String categoryName = categoryNameEditText.getText().toString();
            if (null != categoryName) {
                VolleyUtil.getAllData("ry1oB7LPx",getActivity());
                //VolleyUtil.addCategory(categoryName, getActivity());
                //VolleyUtil.getAllData("ry1oB7LPx",getActivity());
                //CatalogueDatabaseUtil.insertCategory("001", categoryName);

            }
        } else if (view.getId() == R.id.categorycancelbutton) {
            categoryNameEditText.setText("");
            startActivity(new Intent(getActivity(), AndroidDatabaseManager.class));
        }
    }

    @Override
    public Loader<List<Category>> onCreateLoader(int id, Bundle args) {
        return new CategoryLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
        rcAdapter.setCategories(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Category>> loader) {
        rcAdapter.setCategories(new ArrayList<Category>());
    }
}
