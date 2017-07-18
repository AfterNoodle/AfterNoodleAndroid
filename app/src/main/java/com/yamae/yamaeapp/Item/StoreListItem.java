package com.yamae.yamaeapp.Item;

import android.support.annotation.Nullable;

/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListItem {
    int icon,bookmark;
    String storeName, storeDesc;

    public int getIcon() { return icon;}
    public int getBookmark() { return bookmark;}
    public String getStoreName() { return storeName; }
    public String getStoreDesc() { return storeDesc; }

    public StoreListItem(int icon, String storeName, String storeDesc) {
        this.icon = icon;
        this.storeName=storeName;
        this.storeDesc=storeDesc;
    }

    public StoreListItem(int icon, int bookmark, String storeName, String storeDesc){
        this.icon = icon;
        this.bookmark = bookmark;
        this.storeName = storeName;
        this.storeDesc = storeDesc;
    }

}
