package com.mpos.catalogue.model;

/**
 * Created by aarokiax on 12/28/2016.
 */

public class Category {
    private String categoryId;
    private String categoryName;
    private int categoryDeleteIcon;

    public Category() {
    }

    public Category(String categoryId, String categoryName,int categoryDeleteIcon) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDeleteIcon=categoryDeleteIcon;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryDeleteIcon() {
        return categoryDeleteIcon;
    }

    public void setCategoryDeleteIcon(int categoryDeleteIcon) {
        this.categoryDeleteIcon = categoryDeleteIcon;
    }
}
