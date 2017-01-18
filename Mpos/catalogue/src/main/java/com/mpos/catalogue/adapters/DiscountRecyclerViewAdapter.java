package com.mpos.catalogue.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Discount;

import java.util.List;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class DiscountRecyclerViewAdapter extends RecyclerView.Adapter<DiscountRecyclerViewHolders> {

    private List<Discount> discountList;
    private Context context;

    public DiscountRecyclerViewAdapter(Context context, List<Discount> discountList) {
        this.discountList = discountList;
        this.context = context;
    }

    @Override
    public DiscountRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_view_list, null);
        DiscountRecyclerViewHolders rcv = new DiscountRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(DiscountRecyclerViewHolders holder, int position) {
        holder.discountName.setText(discountList.get(position).getDiscountName());
        holder.discountPercent.setText(discountList.get(position).getDiscountPercent());
    }

    @Override
    public int getItemCount() {
        return this.discountList.size();
    }
}
