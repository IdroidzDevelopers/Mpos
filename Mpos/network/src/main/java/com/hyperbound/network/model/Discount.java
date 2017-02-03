package com.hyperbound.network.model;

/**
 * Created by Aron on 1/28/2017.
 */

public class Discount {

    private String discountID;
    private int percentage;
    private String name;

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Discount{" + "discountID='" + discountID + '\'' + ", percentage=" + percentage + ", name='" + name + '\'' + '}';
    }
}
