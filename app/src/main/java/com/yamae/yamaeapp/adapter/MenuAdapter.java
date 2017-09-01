package com.yamae.yamaeapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.item.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songm on 2017-09-01.
 */

public class MenuAdapter extends RecyclerView.Adapter {
    List<MenuItem> items;
    Context mContext;

    public MenuAdapter(Context mContext, List<MenuItem> items) {
        this.mContext = mContext;
        this.items = items;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenuItem curItem = items.get(position);

        ((ViewHolder)holder).txtName.setText(curItem.getName());
        ((ViewHolder)holder).txtPrice.setText(curItem.getPrice()+"원");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}



