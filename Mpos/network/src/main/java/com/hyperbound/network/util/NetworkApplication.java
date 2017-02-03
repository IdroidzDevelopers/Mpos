package com.hyperbound.network.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Aron on 1/28/2017.
 */

public class NetworkApplication{

    private  static Context mContext;

    public static Context getNetworkContext() {
        return mContext;
    }

    public static void setNetworkContext(Context mContext) {
        NetworkApplication.mContext = mContext;
    }
}
