package com.hyperbound.network.model;

/**
 * Created by Aron on 1/28/2017.
 */

public class SubCategory {
    private String subCategoryID;
    private String name;

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubCategory{" + "subCategoryID='" + subCategoryID + '\'' + ", name='" + name + '\'' + '}';
    }
}
