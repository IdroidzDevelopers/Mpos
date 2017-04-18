package com.mpos.catalogue.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.mpos.catalogue.R;
import com.mpos.catalogue.database.CatalogueDatabaseUtil;
import com.mpos.catalogue.model.Category;
import com.mpos.catalogue.model.SubCategory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aarokiax on 2/6/2017.
 */

public class SubcategoryLoader extends AsyncTaskLoader<List<SubCategory>> {

    public SubcategoryLoader(Context context) {
        super(context);
    }

    @Override
    public List<SubCategory> loadInBackground() {
        List<SubCategory> subCategoriesList = new ArrayList<SubCategory>();
        List<SubCategory> subCategorydataList= CatalogueDatabaseUtil.getAllSubCategories();
        if(null!=subCategorydataList) {
            Iterator<SubCategory> subCategoryIterator = subCategorydataList.iterator();
            while (subCategoryIterator.hasNext()) {
                SubCategory data = subCategoryIterator.next();
                subCategoriesList.add(new SubCategory(data.getSubCategoryId(), data.getSubCategoryName(), R.drawable.ic_delete));
            }
        }
        return subCategoriesList;
    }
}
