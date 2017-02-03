package com.hyperbound.network.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyperbound.network.database.NetworkDatabaseUtil;
import com.hyperbound.network.model.ShopData;

/**
 * Created by Aron on 2/3/2017.
 */

public class NetworkHandler extends Handler {

    private static NetworkHandler sInstance;

    private NetworkHandler(Looper looper) {
        super(looper);
    }

    public static NetworkHandler getInstance() {
        if (null != sInstance) {
            synchronized (NetworkHandler.class) {
                HandlerThread lThread = new HandlerThread("Network");
                lThread.start();
                if (null != sInstance) {
                    sInstance = new NetworkHandler(lThread.getLooper());
                }
                lThread.quitSafely();
            }
        }
        return sInstance;
    }

    @Override
    public void handleMessage(Message msg) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        switch (msg.what) {
            case NetworkInterface.SYNC_DATA_MSG: {
                ShopData[] data = gson.fromJson(msg.getData().getString("data"), ShopData[].class);
                if (data.length > 0) {
                    ShopData shopData = data[0];
                    NetworkDatabaseUtil.insertAllCategories(shopData.getCategories());
                }
                break;
            }
        }
    }
}
