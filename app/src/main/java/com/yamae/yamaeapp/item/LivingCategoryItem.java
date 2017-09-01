package com.yamae.yamaeapp.item;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class LivingCategoryItem {
    int backImg;
    String txtLivCatName, id;

    public int getBackImg() { return backImg; }
    public String getTxtLivCatName() { return txtLivCatName;  }

    public String getId() {
        return id;
    }

    public LivingCategoryItem(int backImg, String txtLivCatName, String id){
        this.backImg=backImg;
        this.txtLivCatName=txtLivCatName;
        this.id = id;
    }
}
