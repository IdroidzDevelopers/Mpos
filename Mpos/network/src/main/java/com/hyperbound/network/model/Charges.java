package com.hyperbound.network.model;

/**
 * Created by Aron on 1/28/2017.
 */

public class Charges {

    private int amount;
    private String chargeID;
    private String name;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getChargeID() {
        return chargeID;
    }

    public void setChargeID(String chargeID) {
        this.chargeID = chargeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Charges{" + "amount=" + amount + ", chargeID='" + chargeID + '\'' + ", name='" + name + '\'' + '}';
    }
}
