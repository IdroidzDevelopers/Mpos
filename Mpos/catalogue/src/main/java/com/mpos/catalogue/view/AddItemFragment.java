package com.mpos.catalogue.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.mpos.catalogue.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {

    ImageButton mAddItemImageButton;
    EditText mItemNameEdittext;
    EditText mItemPriceEdittext;
    Spinner mCategorySpinner;
    Spinner mSubcategorySpinner;
    EditText mDescriptionEditText;



    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_add_item, container, false);
        initView(rootview);
        return rootview;
    }

    private void initView(View view){
        mAddItemImageButton=(ImageButton) view.findViewById(R.id.additemimage);
        mItemNameEdittext=(EditText)view.findViewById(R.id.itemname);
        mItemPriceEdittext=(EditText)view.findViewById(R.id.itemprice);
        mDescriptionEditText=(EditText)view.findViewById(R.id.itemdescription);
        mCategorySpinner=(Spinner)view.findViewById(R.id.category_spinner);
        mSubcategorySpinner=(Spinner)view.findViewById(R.id.subcategory_spinner);
    }

}
