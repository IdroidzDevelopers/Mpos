package com.hyperbound.network.util;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hyperbound.network.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aron on 1/28/2017.
 */

public class VolleyUtil {

    private static final String TAG = VolleyUtil.class.getSimpleName();

    /**
     * Method to add a new category
     */
    public static void addCategory(final String categoryName, Context context) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.ADD_CATEGORY_URL;
        JsonObjectRequest request = null;
        try {
            JSONObject requestJson = new JSONObject();
            requestJson.put(NetworkInterface.SHOP_ID_KEY, "ry1oB7LPx");
            JSONObject categoryList = new JSONObject();
            categoryList.put(NetworkInterface.CATEGORY_NAME_KEY, categoryName);
            requestJson.put(NetworkInterface.CATEGORY_KEY, categoryList);
            request = new JsonObjectRequest(lUrl, requestJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }


    /**
     * Method to add a new sub category
     */
    public static void addSubCategory(final String subCategoryName, Context context) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.ADD_SUB_CATEGORY_URL;
        JsonObjectRequest jsObjRequest = null;
        try {
            jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, "createProductRequestForSource() :: onResponse() ::" + response);

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "createProductRequestForSource() :: onErrorResponse() ::" + error);
                        }
                    }) {

                //For passing the value in the body
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> lBodyMap = new HashMap<String, String>();
                    lBodyMap.put(NetworkInterface.SHOP_ID_KEY, "ry1oB7LPx");
                    return lBodyMap;
                }
            };
        } catch (Exception e) {
            Log.e(TAG, "Exception in addSubCategory()", e);
        }

        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    /**
     * Method to add a new discount
     */
    public static void addDiscount(Context context, final String discountName, int percentage) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.ADD_DISCOUNT_URL;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createProductRequestForSource() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createProductRequestForSource() :: onErrorResponse() ::" + error);


                    }
                }) {

            //For passing the value in the body
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> lBodyMap = new HashMap<String, String>();
                lBodyMap.put(NetworkInterface.SHOP_ID_KEY, "ry1oB7LPx");
                return lBodyMap;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    /**
     * Method to add a new charge
     */
    public static void addCharge(Context context, final String chargeName, int amount) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.ADD_CHARGES_URL;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "createProductRequestForSource() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "createProductRequestForSource() :: onErrorResponse() ::" + error);


                    }
                }) {

            //For passing the value in the body
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> lBodyMap = new HashMap<String, String>();
                lBodyMap.put(NetworkInterface.SHOP_ID_KEY, "ry1oB7LPx");
                return lBodyMap;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    /**
     * Method to add a new item
     */
    public static void addItem(final Item item, Context context) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.ADD_ITEM_URL;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "addItem() :: onResponse() ::" + response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "addItem() :: onErrorResponse() ::" + error);


                    }
                }) {

            //For passing the value in the body
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> lBodyMap = new HashMap<String, String>();
                lBodyMap.put(NetworkInterface.SHOP_ID_KEY, "ry1oB7LPx");
                lBodyMap.put(NetworkInterface.CATEGORY_NAME_KEY, item.getName());
                return lBodyMap;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    /**
     * Method to get all the data with shopid
     */
    public static void getAllData(final String shopId, Context context) {
        String lUrl = NetworkInterface.CLOUD_BASE_URL + NetworkInterface.GET_ALL_DATA_BY_SHOP_URL + shopId;

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "getAllData() :: onResponse() ::" + response);
                        try {
                            Bundle lBundle=new Bundle();
                            lBundle.putString("data",response.toString());
                            Message msg=Message.obtain();
                            msg.what=NetworkInterface.SYNC_DATA_MSG;
                            msg.setData(lBundle);
                            NetworkHandler.getInstance().sendMessage(msg);
                            Log.i(TAG, "Response : " + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "getAllData() :: onErrorResponse() ::" + error);


                    }
                });
        VolleySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}
