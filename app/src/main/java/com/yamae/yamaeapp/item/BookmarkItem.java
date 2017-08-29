package com.yamae.yamaeapp.item;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class BookmarkItem {
    int icon, bookmark;
    int storeId;
    String StrName, StrDesc;

    public int getIcon(){return icon;}
    public String getStrName(){return StrName;}
    public String getStrDesc(){return StrDesc;}
    public int getBookmark(){ return bookmark;}

    public int getStoreId() {
        return storeId;
    }

    public BookmarkItem(int icon, int bookmark, JSONObject o){
        this.icon=icon;
        this.bookmark = bookmark;
        try {
            this.StrName=o.getString("title");
            this.storeId =o.getInt("storeId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
