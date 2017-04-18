package com.mpos.catalogue.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;


import com.mpos.catalogue.R;
import com.mpos.catalogue.database.CatalogueDatabaseUtil;
import com.mpos.catalogue.model.Category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by aarokiax on 2/2/2017.
 */

public class CategoryLoader extends AsyncTaskLoader<List<Category>> {
    public CategoryLoader(Context context) {
        super(context);
    }
    @Override
    public List<Category> loadInBackground() {
        List<Category> categoriesList = new ArrayList<Category>();
        List<Category> categorydataList= CatalogueDatabaseUtil.getAllCategories();
        if(null!=categorydataList) {
            Iterator<Category> categoryIterator = categorydataList.iterator();
            while (categoryIterator.hasNext()) {
                Category data = categoryIterator.next();
                categoriesList.add(new Category(data.getCategoryId(), data.getCategoryName(), R.drawable.ic_delete));
            }
        }
        return categoriesList;
    }
}
