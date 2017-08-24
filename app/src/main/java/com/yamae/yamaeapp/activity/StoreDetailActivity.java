package com.yamae.yamaeapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.adapter.StoreListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.StoreListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-25.
 */

public class StoreDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    Intent getIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);
        getIntent = getIntent();

        setToolbar();

        getDB();

    }

    /**
     * 서버에서 db를 가져오는 메소드
     *
     */
    private void getDB() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_MENU+getIntent.getIntExtra("id",-1), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    JSONArray result = new JSONArray();
                    if (jsonObject != null) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = response.getJSONObject(i);

                            JSONObject curObs = new JSONObject();
                            curObs.put("storeId", o.getInt("storeId"));
                            curObs.put("price", o.getInt("price"));
                            curObs.put("name", o.getString("name"));

                            result.put(curObs);
                            Log.e(""+i,"  :  "+curObs.toString());
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

    /**
     * toolbar 설정하는 메소드
     */
    private void setToolbar() {
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
