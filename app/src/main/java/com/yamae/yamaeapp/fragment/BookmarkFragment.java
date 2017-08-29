package com.yamae.yamaeapp.fragment;

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
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yamae.yamaeapp.adapter.BookmarkAdapter;
import com.yamae.yamaeapp.item.BookmarkItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class BookmarkFragment extends Fragment {

    List<BookmarkItem> items;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.view_nothing) FrameLayout viewNothing;
    @BindView(R.id.txt_nothing) TextView txtNothing;

    FirebaseAuth mAuth;
    FirebaseUser curUser;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);


        init();





        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if(curUser==null){
            recyclerView.setVisibility(View.GONE);
            viewNothing.setVisibility(View.VISIBLE);
            txtNothing.setText("로그인을 해주세요");
        } else {
            progressBar.setVisibility(View.VISIBLE);

            items = new ArrayList<>();
          //  items.add(new BookmarkItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"고모네","양도 거지같은데 배달도 안돼요!"));
            adapter = new BookmarkAdapter(items, getActivity());
            recyclerView.setAdapter(adapter);

            if(items.size()>0){
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                viewNothing.setVisibility(View.GONE);
            }else {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                viewNothing.setVisibility(View.VISIBLE);
                txtNothing.setText("목록이 없어요. 즐겨찾기를 눌러 추가해주세요.");
            }
        }


    }
}
