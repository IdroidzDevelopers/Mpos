package com.mpos.catalogue.view;


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
import android.widget.Button;
import android.widget.EditText;


import com.mpos.catalogue.R;
import com.mpos.catalogue.adapters.CategoryRecyclerViewAdapter;
import com.mpos.catalogue.database.CatalogueDatabaseUtil;
import com.mpos.catalogue.model.Category;
import com.mpos.catalogue.model.SubCategory;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment implements View.OnClickListener,LoaderManager.LoaderCallbacks<List<SubCategory>>{

    EditText subCategoryNameEditText;
    Button addSubCategoryButton;
    Button cancelSubCategoryButton;


    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_sub_category, container, false);
        subCategoryNameEditText =(EditText)rootView.findViewById(R.id.subcategoryaddedittext);
        addSubCategoryButton =(Button)rootView.findViewById(R.id.subcategoryaddbutton);
        cancelSubCategoryButton =(Button)rootView.findViewById(R.id.subcategorycancelbutton);
        addSubCategoryButton.setOnClickListener(this);
        cancelSubCategoryButton.setOnClickListener(this);
        List<Category> rowListItem = getAllcategoryList();// TODO : replace with MposDatabaseUtil.getAllCategories();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);

        RecyclerView rView = (RecyclerView)rootView.findViewById(R.id.item_recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        SlideInUpAnimator animator = new SlideInUpAnimator();
        animator.setRemoveDuration(1000);
        animator.setAddDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        rView.setItemAnimator(animator);

        CategoryRecyclerViewAdapter rcAdapter = new CategoryRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {
            if(view.getId()==R.id.subcategoryaddbutton){
                String subCategoryName=subCategoryNameEditText.getText().toString();
                if(null !=subCategoryName){
                    CatalogueDatabaseUtil.insertSubCategory("001",subCategoryName);
                }
            }else if(view.getId()==R.id.subcategorycancelbutton){
                subCategoryNameEditText.setText("");
            }
    }

    private List<Category> getAllcategoryList(){
        List<Category> categoriesList = new ArrayList<Category>();
        categoriesList.add(new Category("001","Eggs",R.drawable.ic_delete));
        categoriesList.add(new Category("002","Breads",R.drawable.ic_delete));
        categoriesList.add(new Category("003","Chinese",R.drawable.ic_delete));
        categoriesList.add(new Category("004","Italian",R.drawable.ic_delete));
        categoriesList.add(new Category("005","Indian Breads",R.drawable.ic_delete));

        return categoriesList;
    }

    @Override
    public Loader<List<SubCategory>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<SubCategory>> loader, List<SubCategory> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<SubCategory>> loader) {

    }
}
