package com.hyperbound.network.util;

/**
 * Created by Aron on 2/2/2017.
 */

public interface NetworkInterface {

    String CLOUD_BASE_URL="http://139.59.8.162";
    String ADD_CATEGORY_URL="/api/shops/category";
    String ADD_SUB_CATEGORY_URL="/api/shops/subCategory";
    String ADD_ITEM_URL="/api/shops/item";
    String ADD_CHARGES_URL="/api/shops/charge";
    String ADD_DISCOUNT_URL="/api/shops/discount";
    String GET_ALL_DATA_BY_SHOP_URL="/api/shops/syncByShop/";

    String SHOP_ID_KEY="shopID";
    String CATEGORY_KEY="category";
    String CATEGORY_NAME_KEY="name";

    int SYNC_DATA_MSG=1;
}
