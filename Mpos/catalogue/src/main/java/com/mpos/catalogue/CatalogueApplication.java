package com.mpos.catalogue;

import android.app.Application;
import android.content.Context;

/**
 * Created by aarokiax on 1/6/2017.
 */

public class CatalogueApplication extends Application{

    public static final String TAG = CatalogueApplication.class.getSimpleName();
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
