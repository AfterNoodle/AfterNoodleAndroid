package com.yamae.yamaeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryFragment extends Fragment {
    public static StoreCategoryFragment newInstance(){
        StoreCategoryFragment fragment = new StoreCategoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_store_category, container, false);
    }
}
