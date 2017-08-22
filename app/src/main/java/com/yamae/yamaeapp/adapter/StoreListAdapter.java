package com.yamae.yamaeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamae.yamaeapp.activity.StoreDetailActivity;
import com.yamae.yamaeapp.item.StoreCategoryItem;
import com.yamae.yamaeapp.item.StoreListItem;
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
    boolean like = false;
    Intent goNext;

    private final int BODY = 0;
    private final int HEADER  = -1;

    public StoreListAdapter(List<StoreListItem> items, List<StoreCategoryItem> items2, Context mContext){ //생성자
        this.items= items;
        this.items2=items2;
        this.mContext = mContext;
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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_list,parent,false);
            return new BodyViewHolder(v);
        }
        else if(viewType==HEADER){  // 리스트 맨위에 랜덤 아이템
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_category,parent,false);
            return new HeaderViewHolder(v);
        }
        else
            return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof BodyViewHolder) {

            final int itemPosition = position-1;    //0번째가 헤더. 7개의 아이템이 들어가면 1~8까지의 position을 잡게 됨.
                                                    // 따라서 body에서 값을 가져다 쓸 때 -1을 해줘야 오버플로우 일으키지 않고 list에서 아이템을 불러옴


            ((BodyViewHolder) holder).imgStr.setImageResource(items.get(itemPosition).getIcon());
            ((BodyViewHolder) holder).txtStrName.setText((items.get(itemPosition).getStoreName()));
            ((BodyViewHolder) holder).txtStrDesc.setText((items.get(itemPosition).getStoreDesc()));
            ((BodyViewHolder) holder).btBookmark.setBackgroundResource(items.get(itemPosition).getBookmark());
            ((BodyViewHolder) holder).btBookmark.setOnClickListener(new View.OnClickListener() {    // 북마크 버튼을 눌렀을 때 이미지 바꾸기0

                @Override
                public void onClick(View v) {

                    if(like==true) {
                        ((BodyViewHolder) holder).btBookmark.setBackgroundResource(R.mipmap.ic_bookmark_empty_c192);
                        like = false;
                    }
                    else {
                        ((BodyViewHolder) holder).btBookmark.setBackgroundResource(R.mipmap.ic_bookmark_c192);
                        like = true;
                    }

                }
            });
            ((BodyViewHolder)holder).itemStrList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNext = new Intent(mContext, StoreDetailActivity.class);
                    goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    goNext.putExtra("Title",items.get(position).getStoreName());
                    goNext.putExtra("Desc",items.get(position).getStoreDesc());
                    goNext.putExtra("bookmark",items.get(position).getBookmark());
                    mContext.startActivity(goNext);
                }
            });
        }
        else if(holder instanceof  HeaderViewHolder) {
            ((HeaderViewHolder) holder).imgRand.setImageResource(items2.get(position).getIcon());
            ((HeaderViewHolder) holder).txtRand.setText(items2.get(position).getCatTitle());
        }
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgStore) ImageView imgStr;
        @BindView(R.id.txtStoreName) TextView txtStrName;
        @BindView(R.id.txtStrDesc) TextView txtStrDesc;
        @BindView(R.id.btBookmark) ImageButton btBookmark;
        @BindView(R.id.itemStrList) FrameLayout itemStrList;
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
