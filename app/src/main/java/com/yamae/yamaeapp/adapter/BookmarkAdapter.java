package com.yamae.yamaeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yamae.yamaeapp.activity.StoreDetailActivity;
import com.yamae.yamaeapp.item.BookmarkItem;
import com.yamae.yamaeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class BookmarkAdapter extends RecyclerView.Adapter{
    List<BookmarkItem> items;
    Context mContext;
    Intent goNext;

    public BookmarkAdapter(List<BookmarkItem> item, Context mContext){
        items = item;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BookmarkItem curItem = items.get(position);
        ((ViewHolder)holder).imgStr.setImageResource(curItem.getIcon());
        ((ViewHolder)holder).txtStrName.setText(curItem.getStrName());
        ((ViewHolder)holder).txtStrDesc.setText(curItem.getStrDesc());
        ((ViewHolder)holder).btBookmark.setBackgroundResource(curItem.getBookmark());
        ((ViewHolder)holder).btBookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(mContext, "누름", Toast.LENGTH_SHORT).show();
            }
        });

        ((ViewHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goNext = new Intent(mContext, StoreDetailActivity.class);
                goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                goNext.putExtra("Title",curItem.getStrName());
                goNext.putExtra("Desc",curItem.getStrDesc());
                goNext.putExtra("bookmark",curItem.getBookmark());
                mContext.startActivity(goNext);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgStore) ImageView imgStr;
        @BindView(R.id.txtStoreName) TextView txtStrName;
        @BindView(R.id.txtStrDesc) TextView txtStrDesc;
        @BindView(R.id.btBookmark) ImageButton btBookmark;
        @BindView(R.id.view) LinearLayout view;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
