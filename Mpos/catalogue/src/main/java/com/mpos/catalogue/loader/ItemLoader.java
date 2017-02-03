package com.mpos.catalogue.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mpos.catalogue.R;
import com.mpos.catalogue.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarokiax on 2/2/2017.
 */

public class ItemLoader extends AsyncTaskLoader<List<Item>> {
    public ItemLoader(Context context) {
        super(context);
    }
    @Override
    public List<Item> loadInBackground() {
        List<Item> allItems = new ArrayList<Item>();
        allItems.add(new Item("United States", R.drawable.add));
        allItems.add(new Item("Canada", R.drawable.add));
        allItems.add(new Item("United Kingdom", R.drawable.add));
        allItems.add(new Item("Germany", R.drawable.add));
        allItems.add(new Item("Sweden", R.drawable.add));
        allItems.add(new Item("United Kingdom", R.drawable.add));
        allItems.add(new Item("Germany", R.drawable.add));
        allItems.add(new Item("Sweden", R.drawable.add));
        allItems.add(new Item("United States", R.drawable.add));
        allItems.add(new Item("Canada", R.drawable.add));
        allItems.add(new Item("United Kingdom", R.drawable.add));
        allItems.add(new Item("Germany", R.drawable.add));
        allItems.add(new Item("Sweden", R.drawable.add));
        allItems.add(new Item("United Kingdom", R.drawable.add));
        allItems.add(new Item("Germany", R.drawable.add));
        allItems.add(new Item("Sweden", R.drawable.add));

        return allItems;
    }
}
