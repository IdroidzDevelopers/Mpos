package com.mpos.catalogue.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mpos.catalogue.R;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class ChargeRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView chargeName;
    public TextView chargePrice;


    public ChargeRecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        chargeName = (TextView)itemView.findViewById(R.id.chargename);
        chargePrice = (TextView)itemView.findViewById(R.id.chargeprice);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
