package com.mpos.catalogue.view;


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
import com.mpos.catalogue.adapters.DiscountRecyclerViewAdapter;
import com.mpos.catalogue.model.Discount;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscountsFragment extends Fragment implements View.OnClickListener{

    EditText discountNameEditText;
    Button addDiscountButton;
    Button cancelDiscountButton;


    public DiscountsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_discounts, container, false);
        discountNameEditText=(EditText)rootView.findViewById(R.id.discountaddedittext);
        addDiscountButton=(Button)rootView.findViewById(R.id.discountaddbutton);
        cancelDiscountButton=(Button)rootView.findViewById(R.id.discountcancelbutton);
        addDiscountButton.setOnClickListener(this);
        cancelDiscountButton.setOnClickListener(this);
        List<Discount> rowListItem = getAllDiscountsList();// TODO : replace with MposDatabaseUtil.getAllCategories();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 2);

        RecyclerView rView = (RecyclerView)rootView.findViewById(R.id.discount_recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        DiscountRecyclerViewAdapter rcAdapter = new DiscountRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);

        return rootView;
    }

    private List<Discount> getAllDiscountsList(){
        List<Discount> discountsList = new ArrayList<Discount>();
        discountsList.add(new Discount(001,"Senior Citizen","20"));
        discountsList.add(new Discount(002,"Student","15"));
        discountsList.add(new Discount(003,"Online coupon","5"));

        return discountsList;
    }
    @Override
    public void onClick(View view) {
            if(view.getId()==R.id.categoryaddbutton){
                String discountName=discountNameEditText.getText().toString();
                if(null !=discountName){
                    //MposDatabaseUtil.insertCategory("001",categoryName);
                }
            }else if(view.getId()==R.id.categorycancelbutton){
                discountNameEditText.setText("");
            }
    }

}
