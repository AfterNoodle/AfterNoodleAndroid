package com.yamae.yamaeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yamae.yamaeapp.Item.BookmarkItem;
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
    Activity activity;

    public BookmarkAdapter(List<BookmarkItem> item, Context mContext){
        items = item;
        activity = (Activity) mContext;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).imgStr.setImageResource(items.get(position).getIcon());
        ((ViewHolder)holder).txtStrName.setText(items.get(position).getStrName());
        ((ViewHolder)holder).txtStrDesc.setText(items.get(position).getStrDesc());
        ((ViewHolder)holder).btBookmark.setBackgroundResource(R.drawable.ic_notifications_black_24dp);
        ((ViewHolder)holder).btBookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(mContext, "누름", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgStore) ImageView imgStr;
        @BindView(R.id.txtStoreName) TextView txtStrName;
        @BindView(R.id.txtStrDesc) TextView txtStrDesc;
        @BindView(R.id.btBookmark) ImageButton btBookmark;

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
