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

import com.yamae.yamaeapp.Item.StoreCategoryItem;
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
    List<StoreCategoryItem> items2;
    Activity activity;

    private final int BODY = 0;
    private final int HEADER  = -1;

    public StoreListAdapter(List<StoreListItem> items, List<StoreCategoryItem> items2, Context mContext){ //생성자
        this.items= items;
        this.items2=items2;
        this.mContext = mContext;
        activity = (Activity)mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return HEADER;
        else if(position<=items.size())
            return BODY;
        else
            return -100;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BODY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_list,null);
            return new BodyViewHolder(v);
        }
        else if(viewType==HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_category,null);
            return new HeaderViewHolder(v);
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final int itemPosition = position;

        if(holder instanceof BodyViewHolder) {
            ((BodyViewHolder) holder).imgStr.setImageResource(items.get(position).getIcon());
            ((BodyViewHolder) holder).txtStrName.setText((items.get(position).getStoreName()));
            ((BodyViewHolder) holder).txtStrDesc.setText((items.get(position).getStoreDesc()));
            ((BodyViewHolder) holder).btBookmark.setBackgroundResource(items.get(position).getBookmark());
        }
        else if(holder instanceof  HeaderViewHolder) {
            ((HeaderViewHolder) holder).imgRand.setBackgroundResource(items2.get(position).getIcon());
            ((HeaderViewHolder) holder).txtRand.setText(items2.get(position).getCatTitle());
        }
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgStore) ImageView imgStr;
        @BindView(R.id.txtStoreName) TextView txtStrName;
        @BindView(R.id.txtStrDesc) TextView txtStrDesc;
        @BindView(R.id.btBookmark) ImageButton btBookmark;
        BodyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgStoreCategory) ImageView imgRand;
        @BindView(R.id.txtStoreItem) TextView txtRand;
        HeaderViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {return items2.size()+items.size(); }

}
