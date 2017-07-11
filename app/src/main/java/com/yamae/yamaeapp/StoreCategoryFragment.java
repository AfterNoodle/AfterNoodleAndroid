package com.yamae.yamaeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryFragment extends Fragment {

    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;
    List<StoreCategoryItem> items;

    public static StoreCategoryFragment newInstance(){
        StoreCategoryFragment fragment = new StoreCategoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
//        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.listStoreCategory);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_store_category,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listStoreCategory);
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.scrollToPosition(0);
//
//        Adapter = new StoreCategoryAdapter(items);
//        recyclerView.setAdapter(Adapter);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        items.add(new StoreCategoryItem(R.drawable.ic_dashboard_black_24dp,"전체"));
        items.add(new StoreCategoryItem(R.drawable.ic_dashboard_black_24dp,"치킨"));
        items.add(new StoreCategoryItem(R.drawable.ic_dashboard_black_24dp,"식사"));
        items.add(new StoreCategoryItem(R.drawable.ic_dashboard_black_24dp,"야식"));



        recyclerView.setAdapter(new StoreCategoryAdapter(items,mContext));

        return view;
    }
}
