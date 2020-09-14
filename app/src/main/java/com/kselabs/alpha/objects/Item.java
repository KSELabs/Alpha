package com.kselabs.alpha.objects;

public class Item {
    private String strDescription;
    private String strTitle;
    private Double dblPrice;

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public Item(String strDescription, Double dblPrice, String strTitle) {
        this.strDescription = strDescription;
        this.dblPrice = dblPrice;
        this.strTitle = strTitle;
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
