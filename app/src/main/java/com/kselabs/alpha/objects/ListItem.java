package com.kselabs.alpha.objects;

public class ListItem {
    private String strTitle;
    private String strDescription;
    private Double dblPrice;

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public ListItem(String strTitle, String strDescription, Double dblPrice) {
        this.strTitle = strTitle;
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
