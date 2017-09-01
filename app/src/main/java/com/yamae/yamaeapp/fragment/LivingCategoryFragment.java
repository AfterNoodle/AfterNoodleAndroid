package com.yamae.yamaeapp.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamae.yamaeapp.adapter.LivingCategoryAdapter;
import com.yamae.yamaeapp.item.LivingCategoryItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class LivingCategoryFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<LivingCategoryItem> mItem;
    String categories[] = {"현대아파트","청솔아파트","매지리","구삼학사","매지기숙사"};
    String categoryIds[] = {"hyu","blu","mea","nin","dom"};

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main,container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mItem = new ArrayList<>();
        for(int i = 0; i< categories.length ; i++)
            mItem.add(new LivingCategoryItem(R.mipmap.liv_cat_hyundae,categories[i],categoryIds[i]));

        recyclerView.setAdapter(new LivingCategoryAdapter(mItem,getActivity()));
        return view;
    }
}
