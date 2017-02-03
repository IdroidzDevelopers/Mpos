package com.hyperbound.network.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.hyperbound.network.model.Category;
import com.hyperbound.network.model.Item;
import com.hyperbound.network.model.SubCategory;
import com.hyperbound.network.util.NetworkApplication;

import java.util.Iterator;
import java.util.List;


/**
 * Created by Aron on 12/28/2016.
 */

public class NetworkDatabaseUtil {

    private static final String TAG = NetworkDatabaseUtil.class.getSimpleName();

    public static Uri insertCategory(String categoryId, String categoryName) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NetworkProvider.CATEGORY_ID, categoryId);
            contentValues.put(NetworkProvider.CATEGORY_NAME, categoryName);
            lUri = NetworkApplication.getNetworkContext().getContentResolver().insert(NetworkProvider.CONTENT_URI_CATEGORY_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- insertCategory ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static Uri insertAllCategories(List<Category> categoryList) {
        Uri lUri = null;
        try {
            List<Category> list=categoryList;
            Iterator<Category> listiterator=list.iterator();
            while (listiterator.hasNext()){
                Category category=listiterator.next();
                ContentValues contentValues = new ContentValues();
                contentValues.put(NetworkProvider.CATEGORY_ID, category.getCategoryID());
                contentValues.put(NetworkProvider.CATEGORY_NAME, category.getName());
                lUri = NetworkApplication.getNetworkContext().getContentResolver().insert(NetworkProvider.CONTENT_URI_CATEGORY_TABLE, contentValues);
                Log.d(TAG, "--(Mpos)-- insertCategory ::" + lUri);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static List<Category> getAllCategories() {

        Cursor lCursor = null;
        List<Category> categoriesList = null;
        try {
            lCursor = NetworkApplication.getNetworkContext().getContentResolver().query(NetworkProvider
                    .CONTENT_URI_CATEGORY_TABLE, null, null, null, null);
            if (lCursor != null) {
                //lCursor.moveToFirst();
                while (lCursor.isLast()) {
                    Category category = new Category();
                    category.setCategoryID(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.CATEGORY_ID)));
                    category.setName(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.CATEGORY_NAME)));
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

    public static Uri insertSubCategory(String subCategoryId, String subCategoryName) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NetworkProvider.SUB_CATEGORY_ID, subCategoryId);
            contentValues.put(NetworkProvider.SUB_CATEGORY_NAME, subCategoryName);
            lUri = NetworkApplication.getNetworkContext().getContentResolver().insert(NetworkProvider.CONTENT_URI_SUBCATEGORY_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- insertSubCategory ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static List<SubCategory> getAllSubCategories() {

        Cursor lCursor = null;
        List<SubCategory> subCategoriesList = null;
        try {
            lCursor = NetworkApplication.getNetworkContext().getContentResolver().query(NetworkProvider
                    .CONTENT_URI_SUBCATEGORY_TABLE, null, null, null, null);
            if (lCursor != null) {
                lCursor.moveToFirst();
                while (lCursor.isLast()) {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setSubCategoryID(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.SUB_CATEGORY_ID)));
                    subCategory.setName(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.SUB_CATEGORY_NAME)));
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

    public static Uri addItem(String itemName, String itemId,String catergoryId,String subCategoryId,int itemPrice,byte[] itemImage) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NetworkProvider.ITEM_NAME, itemName);
            contentValues.put(NetworkProvider.ITEM_ID, itemId);
            contentValues.put(NetworkProvider.ITEM_CATEGORY, catergoryId);
            contentValues.put(NetworkProvider.ITEM_SUB_CATEGORY, subCategoryId);
            contentValues.put(NetworkProvider.ITEM_PRICE, itemPrice);
            contentValues.put(NetworkProvider.ITEM_IMAGE, itemImage);
            lUri = NetworkApplication.getNetworkContext().getContentResolver().insert(NetworkProvider.CONTENT_URI_ITEM_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- addItem ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static List<Item> getAllItems() {

        Cursor lCursor = null;
        List<Item> itemsList = null;
        try {
            lCursor = NetworkApplication.getNetworkContext().getContentResolver().query(NetworkProvider
                    .CONTENT_URI_ITEM_TABLE, null, null, null, null);
            if (lCursor != null) {
                lCursor.moveToFirst();
                while (lCursor.isLast()) {
                    Item items = new Item();
                    items.setItemID(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.ITEM_ID)));
                    items.setName(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.ITEM_NAME)));
                    items.setCategoryName(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.ITEM_CATEGORY)));
                    items.setSubCategoryName(lCursor.getString(lCursor.getColumnIndex(NetworkProvider.ITEM_SUB_CATEGORY)));
                    items.setItemUnitPrice(lCursor.getInt(lCursor.getColumnIndex(NetworkProvider.ITEM_PRICE)));
                    items.setItemImage(lCursor.getBlob(lCursor.getColumnIndex(NetworkProvider.ITEM_IMAGE)));
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
