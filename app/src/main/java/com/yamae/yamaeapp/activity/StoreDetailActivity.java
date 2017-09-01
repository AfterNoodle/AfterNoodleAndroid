package com.yamae.yamaeapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.adapter.MenuAdapter;
import com.yamae.yamaeapp.adapter.StoreListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.MenuItem;
import com.yamae.yamaeapp.item.StoreListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HyunWook Kim on 2017-07-25.
 */

public class StoreDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.itemStrDetCall) FrameLayout itemStrDetCall;
    @BindView(R.id.txtLivPrice) TextView txtLivPrice;
    @BindView(R.id.txtStrTime) TextView txtStrTime;
    @BindView(R.id.txtStrCall) TextView txtStrCall;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.btn_listvisible) Button btnListVisible;

    Intent getIntent;
    RecyclerView.LayoutManager layoutManager;
    Context mContext;
    List<MenuItem> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);
        getIntent = getIntent();

        setToolbar();
        init();

        getDB();

    }

    private void init() {
        mContext=this;
        txtLivPrice.setText(getIntent.getStringExtra("address"));
        txtStrTime.setText(getIntent.getStringExtra("startTime")+ " ~ "+getIntent.getStringExtra("endTime"));
        txtStrCall.setText(getIntent.getStringExtra("phoneNum"));
        items = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

    }

    /**
     * 서버에서 db를 가져오는 메소드
     *
     */
    private void getDB() {
        RequestQueue queue = Volley.newRequestQueue(this);

        Log.e("testesttestid",""+getIntent.getIntExtra("id",-1));

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_MENU+getIntent.getIntExtra("id",-1), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    if (jsonObject != null) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = response.getJSONObject(i);

                            items.add(new MenuItem(response.getJSONObject(i)));
                        }
                        Log.e("testestest",""+items.size());
                        recyclerView.setAdapter(new MenuAdapter(mContext,items));
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

    @OnClick(R.id.itemStrDetCall)
    void onPhoneClicked(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(StoreDetailActivity.this, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(mContext, "전화 권한 설정이 허가 되어있지 않습니다.", Toast.LENGTH_SHORT).show();


            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(StoreDetailActivity.this
                        , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE}
                        , CConstant.MY_PERMISSIONS_REQUEST_PHONE_CALL);
                return;
            }
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(getIntent.getStringExtra("phoneNum")));
            startActivity(intent);
        }
    }

    @OnClick(R.id.btn_listvisible)
    void onListVisibleClicked(){
        if (items.size()<1)
            Toast.makeText(mContext, "일해라 김현욱!!!!!", Toast.LENGTH_SHORT).show();
        else{
               if(recyclerView.getVisibility() == View.GONE){
                recyclerView.setVisibility(View.VISIBLE);
            }else {
                recyclerView.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CConstant.MY_PERMISSIONS_REQUEST_PHONE_CALL:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

}
