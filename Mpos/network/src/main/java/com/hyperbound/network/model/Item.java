package com.hyperbound.network.model;

import android.graphics.Bitmap;

/**
 * Created by Aron on 1/28/2017.
 */

public class Item {

    private String itemID;
    private String name;
    private String categoryName;
    private String subCategoryName;
    private int itemUnitPrice;
    private byte[] itemImage;

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(int itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }
}
