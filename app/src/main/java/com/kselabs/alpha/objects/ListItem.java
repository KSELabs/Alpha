package com.kselabs.alpha.objects;

import android.net.Uri;

public class ListItem {
    private Uri uriImage;
    private String strTitle, strDescription;
    private double dblPrice;
    private int intPosition;

    public ListItem(Uri uriImage, String strTitle, String strDescription, double dblPrice, int intPosition) {
        this.uriImage = uriImage;
        this.strTitle = strTitle;
        this.strDescription = strDescription;
        this.dblPrice = dblPrice;
        this.intPosition = intPosition;
    }

    public Uri getUriImage() {
        return uriImage;
    }

    public void setUriImage(Uri uriImage) {
        this.uriImage = uriImage;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public double getDblPrice() {
        return dblPrice;
    }

    public void setDblPrice(double dblPrice) {
        this.dblPrice = dblPrice;
    }

    public int getIntPosition() {
        return intPosition;
    }

    public void setIntPosition(int intPosition) {
        this.intPosition = intPosition;
    }
}
