package com.yamae.yamaeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.yamae.yamaeapp.adapter.LivingListAdapter;
import com.yamae.yamaeapp.adapter.StoreListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.LivingListItem;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.item.StoreListItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingListActivity extends AppCompatActivity{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.fab) FloatingActionButton fab;


    List<LivingListItem> items;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    Intent getIntent;

    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_list);
        ButterKnife.bind(this);

        init();

        setToolbar();
        getDB();
    }

    /**
     * init 메소드
     */
    private void init() {
        mContext=this;
        getIntent = getIntent();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        items = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
    }

    private void setToolbar() {
        // toolbar 설정
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent.getStringExtra("category"));
    }

    /**
     * 서버에서 가게 db 가져오는 메소드
     */
    private void getDB() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_lIVING+getIntent().getStringExtra("categoriesId"), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    if (jsonObject != null) {
                        Log.e("asdfasdf",""+response.length());
                        for (int i = 0; i < response.length(); i++) {

                            items.add(new LivingListItem(response.getJSONObject(i)));
                        }

                        recyclerView.setAdapter(new LivingListAdapter(items,mContext));
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("asd","ERROR " + error.getMessage());

            }
        });

        queue.add(request);
    }

    @OnClick(R.id.fab)
    void onFabClick(){
        if(mAuth.getCurrentUser()!=null) {
            Intent gotoWrite = new Intent(LivingListActivity.this, LivingWriteActivity.class);
            gotoWrite.putExtra("categoriesId", getIntent.getStringExtra("category"));
            startActivity(gotoWrite);
        }else{
            Toast.makeText(mContext, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LivingListActivity.this,SignInActivity.class));
        }
    }
}
