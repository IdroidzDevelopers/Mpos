package com.mpos.catalogue.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.hyperbound.network.database.NetworkProvider;
import com.hyperbound.network.util.NetworkApplication;
import com.mpos.catalogue.CatalogueApplication;
import com.mpos.catalogue.model.Category;
import com.mpos.catalogue.model.Item;
import com.mpos.catalogue.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarokiax on 12/28/2016.
 */

public class CatalogueDatabaseUtil {

    private static final String TAG = CatalogueDatabaseUtil.class.getSimpleName();

    public static Uri insertCategory(String categoryId, String categoryName) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CatalogueProvider.CATEGORY_ID, categoryId);
            contentValues.put(CatalogueProvider.CATEGORY_NAME, categoryName);
            lUri = CatalogueApplication.getCatalogueContext().getContentResolver().insert(CatalogueProvider.CONTENT_URI_CATEGORY_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- insertCategory ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static Uri insertSubCategory(String subCategoryId, String subCategoryName) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            //contentValues.put(NetworkProvider.CATEGORY_ID, categoryId);
            contentValues.put(CatalogueProvider.SUB_CATEGORY_ID, subCategoryId);
            contentValues.put(CatalogueProvider.SUB_CATEGORY_NAME, subCategoryName);
            lUri = CatalogueApplication.getCatalogueContext().getContentResolver().insert(CatalogueProvider.CONTENT_URI_SUBCATEGORY_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- insertSubCategory ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static List<Category> getAllCategories(){

            String appStatus = null;
            Cursor lCursor = null;
            List<Category> categoriesList=new ArrayList<>();
            try {
                lCursor = CatalogueApplication.getCatalogueContext().getContentResolver().query(CatalogueProvider
                        .CONTENT_URI_CATEGORY_TABLE, null, null, null, null);
                if (lCursor != null) {
                    lCursor.moveToFirst();
                    while (lCursor.moveToNext()) {
                        Category category=new Category();
                        category.setCategoryId(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.CATEGORY_ID)));
                        category.setCategoryName(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.CATEGORY_NAME)));
                        categoriesList.add(category);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception while getting getAppStatus ", e);
            } finally {
                if (null != lCursor) {
                    lCursor.close();
                }
            }
            return categoriesList;

    }

    public static List<SubCategory> getAllSubCategories() {

        Cursor lCursor = null;
        List<SubCategory> subCategoriesList = null;
        try {
            lCursor = CatalogueApplication.getCatalogueContext().getContentResolver().query(CatalogueProvider
                    .CONTENT_URI_SUBCATEGORY_TABLE, null, null, null, null);
            if (lCursor != null) {
                lCursor.moveToFirst();
                while (lCursor.moveToNext()) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setSubCategoryId(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.SUB_CATEGORY_ID)));
                    subCategory.setSubCategoryName(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.SUB_CATEGORY_NAME)));
                    subCategoriesList.add(subCategory);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while getting getAppStatus ", e);
        } finally {
            if (null != lCursor) {
                lCursor.close();
            }
        }
        return subCategoriesList;

    }

    public static List<Item> getAllItems() {

        Cursor lCursor = null;
        List<Item> itemsList = null;
        try {
            lCursor = CatalogueApplication.getCatalogueContext().getContentResolver().query(CatalogueProvider
                    .CONTENT_URI_ITEM_TABLE, null, null, null, null);
            if (lCursor != null) {
                lCursor.moveToFirst();
                while (lCursor.isLast()) {
                    Item items = new Item();
                    items.setItemId(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.ITEM_ID)));
                    items.setItemName(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.ITEM_NAME)));
                    items.setItemCategory(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.ITEM_CATEGORY)));
                    items.setItemSubCategory(lCursor.getString(lCursor.getColumnIndex(CatalogueProvider.ITEM_SUB_CATEGORY)));
                    items.setItemUnitPrice(lCursor.getInt(lCursor.getColumnIndex(CatalogueProvider.ITEM_UNIT_PRICE)));
                    items.setItemImage(lCursor.getBlob(lCursor.getColumnIndex(CatalogueProvider.ITEM_IMAGE)));
                    itemsList.add(items);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception while getting getAppStatus ", e);
        } finally {
            if (null != lCursor) {
                lCursor.close();
            }
        }
        return itemsList;

    }
}
