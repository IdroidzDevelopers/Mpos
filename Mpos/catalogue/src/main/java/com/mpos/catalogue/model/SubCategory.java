package com.mpos.catalogue.model;

/**
 * Created by aarokiax on 12/29/2016.
 */

public class SubCategory {

    private String subCategoryId;
    private String subCategoryName;
    private int categoryDeleteIcon;

    public SubCategory() {
    }

    public SubCategory(String subCategoryId, String subCategoryName, int categoryDeleteIcon) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryDeleteIcon = categoryDeleteIcon;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getCategoryDeleteIcon() {
        return categoryDeleteIcon;
    }

    public void setCategoryDeleteIcon(int categoryDeleteIcon) {
        this.categoryDeleteIcon = categoryDeleteIcon;
    }
}
