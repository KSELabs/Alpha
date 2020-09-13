package com.kselabs.alpha.objects;

import java.util.ArrayList;

public class Category {
    private String strCatName;
    private ArrayList<Item> items;

    public Category(String strCatName) {
        this.strCatName = strCatName;
    }

    public String getStrCatName() {
        return strCatName;
    }

    public void setStrCatName(String strCatName) {
        this.strCatName = strCatName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
