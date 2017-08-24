package com.yamae.yamaeapp.item;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryItem {
    int icon;
    String catTitle;
    String categoryId;

    public int getIcon(){return icon;}
    public String getCatTitle(){return catTitle;}

    public String getCategoryId() {
        return categoryId;
    }

    public StoreCategoryItem(int icon, String catTitle, String categoryId){
        this.icon = icon;
        this.catTitle = catTitle;
        this.categoryId = categoryId;
    }
}
