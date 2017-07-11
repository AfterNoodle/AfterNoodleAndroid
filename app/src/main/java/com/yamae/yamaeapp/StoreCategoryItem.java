package com.yamae.yamaeapp;

import android.graphics.drawable.Drawable;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryItem {
    int icon;
    String CatTitle;

    int getIcon(){return icon;}
    String getCatTitle(){return CatTitle;}

    StoreCategoryItem(int icon, String CatTitle){
        this.icon = icon;
        this.CatTitle = CatTitle;
    }
}
