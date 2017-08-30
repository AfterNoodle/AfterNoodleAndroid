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
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.progressBar) ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseUser curUser;


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

    /**
     * 리스트를 만드는 메소드
     */
    private void setList() {
        items2 = new ArrayList<>();
        items2.add(new StoreCategoryItem(R.mipmap.ic_dice_c192,"랜덤",""));

        items = new ArrayList<>();

        getDB();

    }

    /**
     * 서버에서 가게 db 가져오는 메소드
     */
    private void getDB() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_STORE+getIntent().getStringExtra("categoryId"), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    JSONArray result = new JSONArray();
                    Log.e("ASDf", jsonObject.toString());
                    if (jsonObject != null) {
                        for (int i = 0; i < response.length(); i++) {

                            items.add(new StoreListItem(R.mipmap.logo_rounded, R.mipmap.ic_bookmark_empty_c192, response.getJSONObject(i)));
                        }

                        recyclerView.setAdapter(new StoreListAdapter(items,items2,mContext));
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

    /**
     * 처음 뷰를 선언하는 메소드
     */
    private void init() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();
    }

    /**
     * toolbar 설정하는 메소드
     */
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


    /**
     * 즐겨찾기된 상점 지우는 메소드
     * @param storeId 지울 store id
     * @return 제대로 지워졌는지 체크.
     */
    public int removeFavStore(int storeId){
        final int[] result = new int[1];
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_STOREFAV+curUser.getEmail()+"/"+storeId, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if( response.getJSONObject(0).getInt("ok")==1)
                        result[0] = 1;
                    else
                        result[0] = 0;
                } catch (JSONException e) {
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

        return result[0];
    }

    /**
     *     * 즐겨찾기 상점 추가 메소드
     * @param storeId 추가할 store id
     * @param title 추가할 store 이름
     */
    public void addFavStore(final int storeId,final String title){
        JSONObject params = new JSONObject();
        try {
            params.put("userId",curUser.getEmail());
            params.put("storeId",storeId);
            params.put("title",title);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CConstant.URL_STOREFAV, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("asd","ERROR " + error.getMessage());

            }
        });

        queue.add(request);

        return;
    }
}
