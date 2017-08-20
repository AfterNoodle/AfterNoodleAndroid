package com.yamae.yamaeapp.item;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingListItem {
    String reviewTitle, reviewWriter;
    int rate;

    public String getReviewTitle() {return reviewTitle;}
    public String getReviewWriter() {return reviewWriter;}
    public int getRate(){return rate;}

    public LivingListItem(String reviewTitle, String reviewWriter, int rate){
        this.reviewTitle = reviewTitle;
        this.reviewWriter = reviewWriter;
        this.rate = rate;
    }
}
