package com.hyperbound.network.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Aron on 19-11-2016.
 */

public class ConnectivityUtil {
    private static final String TAG = ConnectivityUtil.class.getSimpleName();

    public static boolean isNetworkConnected(Context aContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
