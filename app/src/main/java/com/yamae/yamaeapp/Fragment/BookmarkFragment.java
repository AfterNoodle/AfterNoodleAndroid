package com.yamae.yamaeapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listBookmark);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        items.add(new BookmarkItem(R.drawable.ic_dashboard_black_24dp,"고모네","양도 거지같은데 배달도 안돼요!"));
        adapter = new BookmarkAdapter(items, mContext);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
