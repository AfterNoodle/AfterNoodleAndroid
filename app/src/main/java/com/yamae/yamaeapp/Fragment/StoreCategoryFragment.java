package com.yamae.yamaeapp.Fragment;

import android.content.Context;
import android.graphics.Color;
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

import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.Adapter.StoreCategoryAdapter;
import com.yamae.yamaeapp.Item.StoreCategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryFragment extends Fragment {

    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<StoreCategoryItem> items;
    Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_store_category,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listStoreCategory);

        // toolbar 설정
        toolbar = (Toolbar) view.findViewById(R.id.defaultToolbar);
        AppCompatActivity appToolbar = (AppCompatActivity) getActivity();
        appToolbar.setSupportActionBar(toolbar);
        appToolbar.setTitle(R.string.title_store);
        appToolbar.setTitleColor(Color.WHITE);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        items.add(new StoreCategoryItem(R.mipmap.logo_rounded,"전체"));
        items.add(new StoreCategoryItem(R.mipmap.logo_rounded,"치킨"));
        items.add(new StoreCategoryItem(R.mipmap.logo_rounded,"식사"));
        items.add(new StoreCategoryItem(R.mipmap.logo_rounded,"야식"));


        recyclerView.setAdapter(new StoreCategoryAdapter(items,mContext));

        return view;
    }
}
