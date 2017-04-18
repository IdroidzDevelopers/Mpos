package com.mpos.catalogue.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

import com.mpos.catalogue.R;
import com.mpos.catalogue.adapters.ItemRecyclerViewAdapter;
import com.mpos.catalogue.loader.ItemLoader;
import com.mpos.catalogue.model.Item;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Item>>,View.OnClickListener {

    ItemRecyclerViewAdapter rcAdapter;
    ImageButton addNewItemButton;


    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        addNewItemButton=(ImageButton)rootView.findViewById(R.id.addnewitembutton);
        addNewItemButton.setOnClickListener(this);
        List<Item> itemsList = new ArrayList();
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);
        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        getLoaderManager().initLoader(2, null, this).forceLoad();
        rcAdapter = new ItemRecyclerViewAdapter(getActivity(), itemsList);
        rView.setAdapter(rcAdapter);
        return rootView;
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
        return new ItemLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {
        rcAdapter.setItems(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        rcAdapter.setItems(new ArrayList<Item>());
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addnewitembutton){
            Fragment fragment = new AddItemFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.bottom_up, R.anim.bottom_down);
            fragmentTransaction.replace(R.id.itemcontent, fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
