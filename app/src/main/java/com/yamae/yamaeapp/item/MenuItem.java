package com.yamae.yamaeapp.item;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by songm on 2017-09-01.
 */

public class MenuItem {
    String name;
    int price, storeId;

    public int getPrice() {
        return price;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }
    public  MenuItem(JSONObject o){
        try {
            this.name = o.getString("name");
            this.price = o.getInt("price");
            this.storeId = o.getInt("storeId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
