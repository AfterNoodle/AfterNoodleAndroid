package com.yamae.yamaeapp.item;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class LivingCategoryItem {
    int backImg;
    String txtLivCatName;

    public int getBackImg() { return backImg; }
    public String getTxtLivCatName() { return txtLivCatName;  }

    public LivingCategoryItem(int backImg, String txtLivCatName){
        this.backImg=backImg;
        this.txtLivCatName=txtLivCatName;
    }
}
