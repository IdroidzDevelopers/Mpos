package com.mpos.catalogue.model;

/**
 * Created by aarokiax on 1/3/2017.
 */

public class Charges {

    private int chargeId;
    private String chargeName;
    private String chargePrice;

    public Charges() {
    }

    public Charges(int chargeId, String chargeName, String chargePrice) {
        this.chargeId = chargeId;
        this.chargeName = chargeName;
        this.chargePrice = chargePrice;
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(String chargePrice) {
        this.chargePrice = chargePrice;
    }
}
