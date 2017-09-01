package com.yamae.yamaeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamae.yamaeapp.activity.LivingListActivity;
import com.yamae.yamaeapp.item.LivingCategoryItem;
import com.yamae.yamaeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class LivingCategoryAdapter extends RecyclerView.Adapter {
    List<LivingCategoryItem> mItem;
     Context mContext;
    Intent goNext;

    public LivingCategoryAdapter(List<LivingCategoryItem> mItem, Context mContext){
        this.mItem = mItem;
        this.mContext = mContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_living_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).imgLivCat.setImageResource(mItem.get(position).getBackImg());
        ((ViewHolder)holder).txtLivCat.setText(mItem.get(position).getTxtLivCatName());
        ((ViewHolder)holder).cardLivCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext = new Intent(mContext.getApplicationContext(),LivingListActivity.class);
                goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                goNext.putExtra("category",mItem.get(position).getTxtLivCatName());
                goNext.putExtra("categoriesId",mItem.get(position).getId());
                mContext.startActivity(goNext);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgLivCat) ImageView imgLivCat;
        @BindView(R.id.cardLivCat) CardView cardLivCat;
        @BindView(R.id.txtLivCat) TextView txtLivCat;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }
}
