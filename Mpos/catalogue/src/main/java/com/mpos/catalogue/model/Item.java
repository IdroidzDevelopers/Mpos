package com.mpos.catalogue.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class Item {

    private String itemId;
    private String itemName;
    private long itemUnitPrice;
    private String itemCategory;
    private String itemSubCategory;
    private byte[] itemImage;

    public Item() {
    }

    public Item(String itemId, String itemName, long itemUnitPrice, String itemCategory, String itemSubCategory, byte[] itemImage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        this.itemCategory = itemCategory;
        this.itemSubCategory = itemSubCategory;
        this.itemImage = itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(long itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemSubCategory() {
        return itemSubCategory;
    }

    public void setItemSubCategory(String itemSubCategory) {
        this.itemSubCategory = itemSubCategory;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }
}
