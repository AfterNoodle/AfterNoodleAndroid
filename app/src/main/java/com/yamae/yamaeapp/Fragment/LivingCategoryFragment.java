package com.yamae.yamaeapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamae.yamaeapp.R;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class LivingCategoryFragment extends Fragment {
    public static LivingCategoryFragment newInstance(){
        LivingCategoryFragment fragment = new LivingCategoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_living_category, container, false);
    }
}
