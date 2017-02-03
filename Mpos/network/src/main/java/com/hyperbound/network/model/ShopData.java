package com.hyperbound.network.model;

import java.util.List;

/**
 * Created by Aron on 1/28/2017.
 */

public class ShopData {

    private String shopID;
    private List<Category> categories;
    private List<SubCategory> subCategories;
    private List<Discount> discounts;
    private List<Charges> charges;


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Charges> getCharges() {
        return charges;
    }

    public void setCharges(List<Charges> charges) {
        this.charges = charges;
    }
}
