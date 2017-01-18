package com.mpos.catalogue.model;

/**
 * Created by aarokiax on 1/2/2017.
 */

public class Discount {

    private int discountId;
    private String discountName;
    private String discountPercent;

    public Discount() {
    }

    public Discount(int discountId, String discountName, String discountPercent) {
        this.discountName = discountName;
        this.discountPercent = discountPercent;
        this.discountId = discountId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }
}
