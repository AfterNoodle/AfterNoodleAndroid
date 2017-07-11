package com.yamae.yamaeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.Item.StoreCategoryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryAdapter extends RecyclerView.Adapter {
    Activity activity;
    List<StoreCategoryItem> mItems;

    public StoreCategoryAdapter(List<StoreCategoryItem> items, Context mContext){
        mItems = items;
        activity = (Activity) mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_category,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        ((ViewHolder)holder).imgStrCat.setImageResource(mItems.get(position).getIcon());
        ((ViewHolder)holder).txtStrName.setText((mItems.get(position).getCatTitle()));

    }

    public int getItemCount(){ return mItems.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgStoreCategory) ImageView imgStrCat;
        @BindView(R.id.txtStoreItem) TextView txtStrName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}