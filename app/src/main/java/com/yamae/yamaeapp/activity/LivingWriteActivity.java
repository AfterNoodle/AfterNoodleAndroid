package com.yamae.yamaeapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.adapter.LivingListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.LivingListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songmho on 2017. 9. 1..
 */

public class LivingWriteActivity extends AppCompatActivity {
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.txt_detail) TextView txtDetail;
    @BindView(R.id.txt_rate) TextView txtRate;
    @BindView(R.id.toolbar) Toolbar toolbar;

    Intent getIntent;
    FirebaseAuth mAuth;
    FirebaseUser curUser;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_write);
        ButterKnife.bind(this);

        mContext = this;
        getIntent = getIntent();
        setToolbar();
        init();


    }

    /**
     * 툴바 설정 메소드
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent.getStringExtra("Title"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtTitle.getText().length()>0 || txtDetail.getText().length()>0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

                    alertDialogBuilder
                            .setMessage("글쓰기를 종료하시겠습니까")
                            .setCancelable(false)
                            .setPositiveButton("네",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            // 프로그램을 종료한다
                                            finish();
                                        }
                                    })
                            .setNegativeButton("아니요",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            // 다이얼로그를 취소한다
                                            dialog.cancel();
                                        }
                                    });
                    alertDialogBuilder.show();
                }
                else
                    finish();
            }
        });
    }

    /**
     * 초기화 메소드
     */
    private void init() {
        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_living_write, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_submit){
            sendWrite();

        }
        return true;
    }

    /**
     * 글쓰기 완료 후 전송 메소드
     */
    private void sendWrite() {
        JSONObject params = new JSONObject();
        try {
            params.put("category",getIntent.getStringExtra("categoriesId"));
            params.put("detail",txtDetail.getText().toString());
            params.put("title",txtTitle.getText().toString());
            params.put("rate",Double.parseDouble(txtRate.getText().toString()));
            params.put("userId",curUser.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CConstant.URL_lIVING, params, new Response.Listener<JSONObject>() {
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
    }
}
