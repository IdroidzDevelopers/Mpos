package com.hyperbound.network.model;

/**
 * Created by Aron on 1/28/2017.
 */

public class Category {

    private String categoryID;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryID='" + categoryID + '\'' + ", name='" + name + '\'' + '}';
    }
}
