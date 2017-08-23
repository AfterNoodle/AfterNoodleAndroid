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
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yamae.yamaeapp.adapter.StoreListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.StoreCategoryItem;
import com.yamae.yamaeapp.item.StoreListItem;
import com.yamae.yamaeapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListActivity extends AppCompatActivity {

    List<StoreListItem> items;
    List<StoreCategoryItem> items2;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    Intent getIntent;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        ButterKnife.bind(this);
        mContext=this;
        getIntent = getIntent();

        init();

        setToolbar();

        setList();
    }

    private void setList() {
        items2 = new ArrayList<>();
        items2.add(new StoreCategoryItem(R.mipmap.ic_dice_c192,"랜덤"));

        items = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_STORE, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    JSONArray result = new JSONArray();
                    Log.e("ASDf", jsonObject.toString());
                    if (jsonObject != null) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = response.getJSONObject(i);

                            JSONObject curObs = new JSONObject();
                            curObs.put("id", o.getInt("id"));
                            curObs.put("category", o.getString("category"));
                            curObs.put("title", o.getString("title"));
                            curObs.put("phoneNum", o.getString("phoneNum"));
                            curObs.put("startTime", o.getString("startTime"));
                            curObs.put("endTime", o.getString("endTime"));
                            curObs.put("isDelivery", o.getBoolean("isDelivery"));
                            curObs.put("address", o.getString("address"));

                            result.put(curObs);

                            items.add(new StoreListItem(R.mipmap.logo_rounded, R.mipmap.ic_bookmark_empty_c192, curObs.getString("title"), "맛은 있다만 양도 가격도 창렬!"));

                            recyclerView.setAdapter(new StoreListAdapter(items,items2,mContext));
                        }
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

    private void init() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        // toolbar 설정
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent.getStringExtra("title"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
