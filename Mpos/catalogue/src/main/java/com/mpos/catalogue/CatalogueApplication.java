package com.mpos.catalogue;

import android.app.Application;
import android.content.Context;

/**
 * Created by aarokiax on 1/6/2017.
 */

public class CatalogueApplication{

    public static final String TAG = CatalogueApplication.class.getSimpleName();
    private static Context mContext;



    public static void setCatalogueContext(Context context){
        mContext=context;
    }
    public static Context getCatalogueContext() {
        return mContext;
    }
}
