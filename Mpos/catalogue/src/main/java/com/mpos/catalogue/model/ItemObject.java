package com.mpos.catalogue.model;

import android.widget.ImageView;

/**
 * Created by aarokiax on 12/26/2016.
 */

public class ItemObject {

   // private String itemName;
    private int itemImage;

    public ItemObject(String itemName, int itemImage) {
       // this.itemName = itemName;
        this.itemImage = itemImage;
    }

    /*public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }*/

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
