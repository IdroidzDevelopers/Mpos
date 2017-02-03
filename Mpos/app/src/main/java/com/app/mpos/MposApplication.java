package com.app.mpos;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.hyperbound.network.util.NetworkApplication;
import com.mpos.catalogue.CatalogueApplication;

/**
 * Created by aarokiax on 12/28/2016.
 */

public class MposApplication extends Application {

    public static final String TAG = MposApplication.class.getSimpleName();
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "inside onCreate() ");
        createMposTable(this);
        sContext = this;
        CatalogueApplication.setCatalogueContext(this);
        NetworkApplication.setNetworkContext(this);
    }

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context){
        sContext=context;
    }


    private void createMposTable(Context aContext) {
        try {
            ContentValues lValues = new ContentValues();
            lValues.put(MposConstants.TABLE_MASTER_PACKAGE, MposConstants.MPOS_PACKAGE);
            Uri lUri = aContext.getContentResolver().insert(Uri.parse(MposConstants.CONTENT_URI_TABLE_MASTER),
                    lValues);
            if (null != lUri) {
                Log.d(TAG, "Table creation successful");
            } else {
                Log.e(TAG, "Table creation failed :: createAncsTable");
            }
        } catch (Exception e) {
            Log.e(TAG, "Table creation failed " + e);
        }
    }
}
