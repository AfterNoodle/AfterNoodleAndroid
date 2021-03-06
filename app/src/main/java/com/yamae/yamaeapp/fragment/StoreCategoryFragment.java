package com.yamae.yamaeapp.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.adapter.StoreCategoryAdapter;
import com.yamae.yamaeapp.item.StoreCategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<StoreCategoryItem> items;

    String categories[] = {"한식","중식","양식","치킨","야식","카페","술집","랜덤"};
    String categoryIds[] = {"korean","chinese","western","chicken","night","cafe","alcohol","random"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();

        for(int i=0;i<categories.length;i++)
            items.add(new StoreCategoryItem(R.mipmap.logo_rounded,categories[i],categoryIds[i]));


        recyclerView.setAdapter(new StoreCategoryAdapter(items,getActivity()));

        return view;
    }
}
