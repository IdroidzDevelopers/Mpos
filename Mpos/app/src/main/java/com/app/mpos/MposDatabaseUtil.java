package com.app.mpos;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.mpos.catalogue.model.Category;

import java.util.List;

/**
 * Created by aarokiax on 12/28/2016.
 */

public class MposDatabaseUtil {

    private static final String TAG = MposDatabaseUtil.class.getSimpleName();

    public static Uri insertCategory(String categoryId, String categoryName) {
        Uri lUri = null;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MposProvider.CATEGORY_ID, categoryId);
            contentValues.put(MposProvider.CATEGORY_NAME, categoryName);
            lUri = MposApplication.getContext().getContentResolver().insert(MposProvider.CONTENT_URI_CATEGORY_TABLE, contentValues);
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
            //contentValues.put(MposProvider.CATEGORY_ID, categoryId);
            contentValues.put(MposProvider.SUB_CATEGORY_ID, subCategoryId);
            contentValues.put(MposProvider.SUB_CATEGORY_NAME, subCategoryName);
            lUri = MposApplication.getContext().getContentResolver().insert(MposProvider.CONTENT_URI_SUBCATEGORY_TABLE, contentValues);
            Log.d(TAG, "--(Mpos)-- insertSubCategory ::" + lUri);
        } catch (Exception e) {
            Log.e(TAG, "Exception happened in insert::" + e.getMessage());
        }

        return lUri;
    }

    public static List<Category> getAllCategories(){

            String appStatus = null;
            Cursor lCursor = null;
            List<Category> categoriesList=null;
            try {
                lCursor = MposApplication.getContext().getContentResolver().query(MposProvider
                        .CONTENT_URI_CATEGORY_TABLE, null, null, null, null);
                if (lCursor != null) {
                    lCursor.moveToFirst();
                    while (lCursor.isLast()) {
                        Category category=new Category();
                        category.setCategoryId(lCursor.getString(lCursor.getColumnIndex(MposProvider.CATEGORY_ID)));
                        category.setCategoryName(lCursor.getString(lCursor.getColumnIndex(MposProvider.CATEGORY_NAME)));
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
}
