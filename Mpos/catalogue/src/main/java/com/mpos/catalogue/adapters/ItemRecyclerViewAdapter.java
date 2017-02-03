package com.mpos.catalogue.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Item;

import java.util.List;


/**
 * Created by aarokiax on 12/26/2016.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewHolders> {

    private List<Item> itemList;
    private Context context;

    public ItemRecyclerViewAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setItems(List<Item> data){
        itemList=data;
        notifyDataSetChanged();
    }

    @Override
    public ItemRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_list, null);
        ItemRecyclerViewHolders rcv = new ItemRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ItemRecyclerViewHolders holder, int position) {
       // holder.countryName.setText(itemList.get(position).getItemName());
        holder.countryPhoto.setImageResource(itemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
