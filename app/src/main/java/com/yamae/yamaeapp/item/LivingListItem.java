package com.yamae.yamaeapp.item;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingListItem {
    String reviewTitle, reviewWriter;
    double rate;
    String id, detail;

    public String getReviewTitle() {return reviewTitle;}
    public String getReviewWriter() {return reviewWriter;}

    public String getDetail() {
        return detail;
    }

    public double getRate(){return rate;}

    public String getId() {
        return id;
    }

    public LivingListItem(JSONObject o) {
        try {
            id = o.getString("_id");
            reviewTitle = o.getString("title");
            reviewWriter = o.getString("userId");
            rate = o.getDouble("rate");
            detail = o.getString("detail");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
