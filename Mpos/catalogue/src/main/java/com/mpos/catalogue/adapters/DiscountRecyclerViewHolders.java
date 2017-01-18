package com.mpos.catalogue.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mpos.catalogue.R;


/**
 * Created by aarokiax on 12/26/2016.
 */

public class DiscountRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView discountName;
    public TextView discountPercent;


    public DiscountRecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        discountName = (TextView)itemView.findViewById(R.id.discountname);
        discountPercent = (TextView)itemView.findViewById(R.id.discountpercent);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
