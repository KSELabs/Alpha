package com.kselabs.alpha.objects;

import com.kselabs.alpha.adapters.MemberAdp;

import java.util.ArrayList;

public class CategoryItem {
    private String strCatName;
    private ArrayList<ListItem> ListItems;
    private MemberAdp memberAdp;

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

    public MemberAdp getMemberAdp() {
        return memberAdp;
    }

    public void setMemberAdp(MemberAdp memberAdp) {
        this.memberAdp = memberAdp;
    }
}
