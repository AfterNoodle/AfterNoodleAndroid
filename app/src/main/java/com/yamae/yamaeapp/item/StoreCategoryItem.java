package com.yamae.yamaeapp.item;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryItem {
    int icon;
    String CatTitle;

    public int getIcon(){return icon;}
    public String getCatTitle(){return CatTitle;}

    public StoreCategoryItem(int icon, String CatTitle){
        this.icon = icon;
        this.CatTitle = CatTitle;
    }
}
