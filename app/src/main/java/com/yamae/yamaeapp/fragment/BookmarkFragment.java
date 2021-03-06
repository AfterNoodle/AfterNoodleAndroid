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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yamae.yamaeapp.adapter.BookmarkAdapter;
import com.yamae.yamaeapp.adapter.StoreListAdapter;
import com.yamae.yamaeapp.constant.CConstant;
import com.yamae.yamaeapp.item.BookmarkItem;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.item.StoreListItem;

import org.json.JSONArray;
import org.json.JSONException;

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


        items = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if(curUser==null){
            recyclerView.setVisibility(View.GONE);
            viewNothing.setVisibility(View.VISIBLE);
            txtNothing.setText("로그인을 해주세요");
        } else {
            progressBar.setVisibility(View.VISIBLE);

            getList();
        }


    }

    public void getList() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, CConstant.URL_STOREFAV+curUser.getEmail(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    JSONArray jsonObject = response;
                    Log.e("ASDf", jsonObject.toString());
                    if (jsonObject != null) {
                        for (int i = 0; i < response.length(); i++) {
                            items.add(new BookmarkItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,response.getJSONObject(i)));
                        }
                        recyclerView.setAdapter( new BookmarkAdapter(items, getActivity(),BookmarkFragment.this));
                        progressBar.setVisibility(View.GONE);
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

        return ;
    }

    /**
     * 즐겨찾기된 상점 지우는 메소드
     * @param storeId 지울 store id
     * @return 제대로 지워졌는지 체크.
     */
    public int removeFavStore(int storeId){
        final int[] result = new int[1];
        RequestQueue queue = Volley.newRequestQueue(getActivity());

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
}
