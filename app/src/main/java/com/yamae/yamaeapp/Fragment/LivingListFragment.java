package com.yamae.yamaeapp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamae.yamaeapp.Adapter.LivingListAdapter;
import com.yamae.yamaeapp.Item.LivingListItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingListFragment extends Fragment{

    List<LivingListItem> items;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_living_list,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listLiv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        // toolbar 설정
        toolbar = (Toolbar) view.findViewById(R.id.defaultToolbar);
        AppCompatActivity appToolbar = (AppCompatActivity) getActivity();
        appToolbar.setSupportActionBar(toolbar);
//        appToolbar.setTitle(R.string.title_store);
        appToolbar.setTitleColor(Color.WHITE);

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
        return view;
    }
}
