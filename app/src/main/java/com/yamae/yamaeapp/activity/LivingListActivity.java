package com.yamae.yamaeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yamae.yamaeapp.adapter.LivingListAdapter;
import com.yamae.yamaeapp.item.LivingListItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingListActivity extends AppCompatActivity{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;


    List<LivingListItem> items;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    Intent getIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_list);
        ButterKnife.bind(this);
        mContext=this;
        getIntent = getIntent();
        setToolbar();


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);


        items = new ArrayList<>();
        items.add(new LivingListItem("현대아파트 103동 301호","김현욱",5));
        items.add(new LivingListItem("현대 103동 302호","송명호",40));
        items.add(new LivingListItem("매지마트 205호","이원준",25));
        items.add(new LivingListItem("세연1학사 1401호","최수진",15));
        items.add(new LivingListItem("매지2학사 306호","박준수",20));
        items.add(new LivingListItem("한솥 203호","전도영",35));
        items.add(new LivingListItem("카피랜드 304호","김현중",30));
        items.add(new LivingListItem("독수리 요새 204호","남재희",45));

        recyclerView.setAdapter(new LivingListAdapter(items,mContext));
    }

    private void setToolbar() {
        // toolbar 설정
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent.getStringExtra("Title"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
