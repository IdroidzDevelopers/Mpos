package com.mpos.catalogue.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpos.catalogue.R;
import com.mpos.catalogue.adapters.ItemRecyclerViewAdapter;
import com.mpos.catalogue.model.ItemObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {


    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_item, container, false);
        List<ItemObject> rowListItem = getAllItemList();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);

        RecyclerView rView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        ItemRecyclerViewAdapter rcAdapter = new ItemRecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    private List<ItemObject> getAllItemList(){
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("United States", R.drawable.add));
        allItems.add(new ItemObject("Canada", R.drawable.add));
        allItems.add(new ItemObject("United Kingdom", R.drawable.add));
        allItems.add(new ItemObject("Germany", R.drawable.add));
        allItems.add(new ItemObject("Sweden", R.drawable.add));
        allItems.add(new ItemObject("United Kingdom", R.drawable.add));
        allItems.add(new ItemObject("Germany", R.drawable.add));
        allItems.add(new ItemObject("Sweden", R.drawable.add));
        allItems.add(new ItemObject("United States", R.drawable.add));
        allItems.add(new ItemObject("Canada", R.drawable.add));
        allItems.add(new ItemObject("United Kingdom", R.drawable.add));
        allItems.add(new ItemObject("Germany", R.drawable.add));
        allItems.add(new ItemObject("Sweden", R.drawable.add));
        allItems.add(new ItemObject("United Kingdom", R.drawable.add));
        allItems.add(new ItemObject("Germany", R.drawable.add));
        allItems.add(new ItemObject("Sweden", R.drawable.add));

        return allItems;
    }

}
