package com.yamae.yamaeapp.java;

import com.yamae.yamaeapp.R;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class RateImage {
    double rate;

    public RateImage(){
        this.rate = 5;

        }
    public int getRateColorImage(double rate){
        this.rate = rate;
        int img = 0;
        if(rate == .5)        img = R.mipmap.ic_rate05_c48;
        else if(rate == 1.0)  img = R.mipmap.ic_rate10_c48;
        else if(rate == 1.5)  img = R.mipmap.ic_rate15_c48;
        else if(rate == 2.0)  img = R.mipmap.ic_rate20_c48;
        else if(rate == 2.5)  img = R.mipmap.ic_rate25_c48;
        else if(rate == 3.0)  img = R.mipmap.ic_rate30_c48;
        else if(rate == 3.5)  img = R.mipmap.ic_rate35_c48;
        else if(rate == 4.0)  img = R.mipmap.ic_rate40_c48;
        else if(rate == 4.5)  img = R.mipmap.ic_rate45_c48;
        else if(rate == 5.0)  img = R.mipmap.ic_rate50_c48;
        return img;
    }

    public int getRateWhiteImage(int rate){
        this.rate = rate;
        int img = 0;
        switch (rate){
            case 5: img = R.mipmap.ic_rate05_w48;
                break;
            case 10: img = R.mipmap.ic_rate10_w48;
                break;
            case 15: img = R.mipmap.ic_rate15_w48;
                break;
            case 20: img = R.mipmap.ic_rate20_w48;
                break;
            case 25: img = R.mipmap.ic_rate25_w48;
                break;
            case 30: img = R.mipmap.ic_rate30_w48;
                break;
            case 35: img = R.mipmap.ic_rate35_w48;
                break;
            case 40: img = R.mipmap.ic_rate40_w48;
                break;
            case 45: img = R.mipmap.ic_rate45_w48;
                break;
            case 50: img = R.mipmap.ic_rate50_w48;
                break;
            default: img = R.mipmap.ic_rate05_w48;
                break;

        }
        return img;
    }
}
