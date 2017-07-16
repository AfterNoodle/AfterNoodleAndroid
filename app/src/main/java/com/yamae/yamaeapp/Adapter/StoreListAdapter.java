package com.yamae.yamaeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamae.yamaeapp.Item.StoreListItem;
import com.yamae.yamaeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListAdapter extends RecyclerView.Adapter{

    Context mContext;
    List<StoreListItem> items;
    Activity activity;

    public StoreListAdapter(List<StoreListItem> items, Context mContext){
        this.items= items;
        activity = (Activity)mContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_list,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).btBookmark.setImageResource(R.mipmap.ic_bookmark_c192);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.imgStore) ImageView imgStr;
//        @BindView(R.id.txtStoreName) TextView txtStrName;
//        @BindView(R.id.txtStrDesc) TextView txtStrDesc;
        @BindView(R.id.btBookmark) ImageButton btBookmark;
        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {return 0; }
}
