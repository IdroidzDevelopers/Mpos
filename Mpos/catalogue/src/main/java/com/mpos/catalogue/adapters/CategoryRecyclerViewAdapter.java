package com.mpos.catalogue.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Category;

import java.util.List;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryRecyclerViewHolders>{

    public List<Category> itemList;
    private Context context;

    public CategoryRecyclerViewAdapter(Context context, List<Category> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setCategories(List<Category> categories){
        this.itemList=categories;
        notifyDataSetChanged();
    }

    @Override
    public CategoryRecyclerViewAdapter.CategoryRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_list, null);
        CategoryRecyclerViewHolders rcv = new CategoryRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(CategoryRecyclerViewAdapter.CategoryRecyclerViewHolders holder, final int position) {
        holder.categoryName.setText(itemList.get(position).getCategoryName());
        holder.categoryDeleteIcon.setImageResource(itemList.get(position).getCategoryDeleteIcon());
        holder.categoryDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,itemList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }




    public static class CategoryRecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView categoryName;
        public ImageView categoryDeleteIcon;


        public CategoryRecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryName = (TextView)itemView.findViewById(R.id.categoryname);
            categoryDeleteIcon=(ImageView)itemView.findViewById(R.id.categorydeleteicon);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked  Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }


    }
}


