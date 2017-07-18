package com.yamae.yamaeapp.Item;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class BookmarkItem {
    int icon, bookmark;
    String StrName, StrDesc;

    public int getIcon(){return icon;}
    public String getStrName(){return StrName;}
    public String getStrDesc(){return StrDesc;}
    public int getBookmark(){ return bookmark;}

    public BookmarkItem(int icon,int bookmark, String StrName, String StrDesc){
        this.icon=icon;
        this.bookmark = bookmark;
        this.StrName=StrName;
        this.StrDesc=StrDesc;
    }
}
