package com.mpos.catalogue.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Charges;

import java.util.List;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class ChargeRecyclerViewAdapter extends RecyclerView.Adapter<ChargeRecyclerViewHolders> {

    private List<Charges> chargeList;
    private Context context;

    public ChargeRecyclerViewAdapter(Context context, List<Charges> itemList) {
        this.chargeList = itemList;
        this.context = context;
    }

    @Override
    public ChargeRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.charge_view_list, null);
        ChargeRecyclerViewHolders rcv = new ChargeRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ChargeRecyclerViewHolders holder, int position) {
        holder.chargeName.setText(chargeList.get(position).getChargeName());
        holder.chargePrice.setText(chargeList.get(position).getChargePrice());
       // holder.countryPhoto.setImageResource(itemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return this.chargeList.size();
    }
}
