package com.yamae.yamaeapp.Item;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class BookmarkItem {
    int icon;
    String StrName, StrDesc;

    public int getIcon(){return icon;}
    public String getStrName(){return StrName;}
    public String getStrDesc(){return StrDesc;}

    public BookmarkItem(int icon, String StrName, String StrDesc){
        this.icon=icon;
        this.StrName=StrName;
        this.StrDesc=StrDesc;
    }
}
