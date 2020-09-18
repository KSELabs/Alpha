package com.kselabs.alpha.objects;

import java.util.ArrayList;

public class CategoryItem {
    private String strCatName;
    private ArrayList<ListItem> ListItems;

    public CategoryItem(String strCatName, ArrayList<ListItem> listItems) {
        this.strCatName = strCatName;
        ListItems = listItems;
    }

    public String getStrCatName() {
        return strCatName;
    }

    public void setStrCatName(String strCatName) {
        this.strCatName = strCatName;
    }

    public ArrayList<ListItem> getListItems() {
        return ListItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        ListItems = listItems;
    }
}
