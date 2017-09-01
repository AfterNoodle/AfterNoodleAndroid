package com.yamae.yamaeapp.item;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListItem {
    int icon,bookmark;

    int id;
    String title;
    String category;
    String phoneNum;
    String startTime;
    String endTime;
    boolean isDelivery;
    String address;

    public int getIcon(){return icon;}

    public int getId() {
        return id;
    }

    public String getTitle(){return title;}

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getBookmark() { return bookmark;}


    public StoreListItem(int icon, int bookmark, JSONObject o){
        this.icon = icon;
        this.bookmark = bookmark;
        try {
            this.title = o.getString("title");
            this.category = o.getString("category");
            this.phoneNum = o.getString("phoneNum");
            this.startTime = o.getString("startTime");
            this.endTime = o.getString("endTime");
            this.address = o.getString("address");
            this.id = o.getInt("id");
            this.isDelivery = o.getBoolean("isDelivery");
            Log.e("asdf",o.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
