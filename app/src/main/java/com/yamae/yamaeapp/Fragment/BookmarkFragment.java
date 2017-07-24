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

import com.yamae.yamaeapp.Adapter.BookmarkAdapter;
import com.yamae.yamaeapp.Item.BookmarkItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class BookmarkFragment extends Fragment {

    List<BookmarkItem> items;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Context mContext;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listBookmark);

        // toolbar 설정
        toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.defaultToolbar);
        AppCompatActivity appToolbar = (AppCompatActivity) getActivity();
        appToolbar.setSupportActionBar(toolbar);
        appToolbar.setTitle(R.string.title_bookmark);
        appToolbar.setTitleColor(Color.WHITE);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        items.add(new BookmarkItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"고모네","양도 거지같은데 배달도 안돼요!"));
        adapter = new BookmarkAdapter(items, mContext);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
