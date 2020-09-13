package com.kselabs.alpha.objects;

public class Item {
    private String strDescription;
    private Double dblPrice;

    public Item(String strDescription, Double dblPrice) {
        this.strDescription = strDescription;
        this.dblPrice = dblPrice;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public Double getDblPrice() {
        return dblPrice;
    }

    public void setDblPrice(Double dblPrice) {
        this.dblPrice = dblPrice;
    }
}
