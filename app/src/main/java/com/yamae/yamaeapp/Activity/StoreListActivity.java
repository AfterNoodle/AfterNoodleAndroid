package com.yamae.yamaeapp.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yamae.yamaeapp.Adapter.StoreListAdapter;
import com.yamae.yamaeapp.Item.StoreCategoryItem;
import com.yamae.yamaeapp.Item.StoreListItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListActivity extends AppCompatActivity {

    List<StoreListItem> items;
    List<StoreCategoryItem> items2;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        recyclerView = (RecyclerView) findViewById(R.id.listStore);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        mContext=StoreListActivity.this;

        // toolbar 설정
        toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        AppCompatActivity appToolbar = (AppCompatActivity) mContext;
        appToolbar.setSupportActionBar(toolbar);
        appToolbar.setTitle(R.string.title_store);
        appToolbar.setTitleColor(Color.WHITE);

        items2 = new ArrayList<>();
        items2.add(new StoreCategoryItem(R.mipmap.ic_dice_c192,"랜덤"));

        items = new ArrayList<>();
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"BHC","맛은 있다만 양도 가격도 창렬!"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"1","맛은 있다만 양도 가격도 창렬!"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"22","맛은 있다만 양도 가격도"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"333","맛은 있다만 양도"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"4444","맛은 있다만"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"55555","맛은"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_empty_c192,"666666","맛!"));


        recyclerView.setAdapter(new StoreListAdapter(items,items2,mContext));
    }
}
