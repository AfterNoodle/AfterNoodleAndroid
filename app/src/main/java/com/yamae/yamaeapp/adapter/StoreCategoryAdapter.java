package com.yamae.yamaeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.internal.ForegroundLinearLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yamae.yamaeapp.activity.StoreListActivity;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.item.StoreCategoryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class StoreCategoryAdapter extends RecyclerView.Adapter {
   List<StoreCategoryItem> mItems;
    Context mContext;
    Intent goNext;

    public StoreCategoryAdapter(List<StoreCategoryItem> items, Context mContext){
        mItems = items;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_category,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position){
        final StoreCategoryItem curItme = mItems.get(position);

        ((ViewHolder)holder).imgStrCat.setImageResource(curItme.getIcon());
        ((ViewHolder)holder).txtStrName.setText(curItme.getCatTitle());
        ((ViewHolder)holder).itemStrCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                goNext = new Intent(mContext.getApplicationContext(),StoreListActivity.class);
                goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                goNext.putExtra("title",curItme.getCatTitle());
                goNext.putExtra("categoryId",curItme.getCategoryId());
                mContext.startActivity(goNext);

            }
        });

    }

    public int getItemCount(){ return mItems.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgStoreCategory) ImageView imgStrCat;
        @BindView(R.id.txtStoreItem) TextView txtStrName;
        @BindView(R.id.itemStoreCategory) LinearLayout itemStrCat;
        @BindView(R.id.itemStrCatLayout) LinearLayout itemStrCatLayout;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
