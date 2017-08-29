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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yamae.yamaeapp.activity.SignInActivity;
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

            final StoreListItem curItem = items.get(position-1);

            ((BodyViewHolder) holder).imgStr.setImageResource(curItem.getIcon());
            ((BodyViewHolder) holder).txtStrName.setText(curItem.getTitle());
            ((BodyViewHolder) holder).txtStrDesc.setText("asdfasdf");
            ((BodyViewHolder) holder).btBookmark.setBackgroundResource(curItem.getBookmark());
            ((BodyViewHolder) holder).btBookmark.setOnClickListener(new View.OnClickListener() {    // 북마크 버튼을 눌렀을 때 이미지 바꾸기0

                @Override
                public void onClick(View v) {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    FirebaseUser curUser = mAuth.getCurrentUser();
                    if(curUser != null){       //로그인 되어있을 때
                        if(like==true) {
                            ((BodyViewHolder) holder).btBookmark.setBackgroundResource(R.mipmap.ic_bookmark_empty_c192);
                            like = false;
                        }
                        else {
                            ((BodyViewHolder) holder).btBookmark.setBackgroundResource(R.mipmap.ic_bookmark_c192);
                            like = true;
                        }
                    } else {    //안돼있을 때
                        goNext = new Intent(mContext, SignInActivity.class);
                        goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(goNext);
                    }

                }
            });
            ((BodyViewHolder)holder).itemStrList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goNext = new Intent(mContext, StoreDetailActivity.class);
                    goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    goNext.putExtra("Title",curItem.getTitle());
                    goNext.putExtra("Desc","asdfasdf");
                    goNext.putExtra("id",curItem.getId());
                    goNext.putExtra("bookmark",curItem.getBookmark());
                    mContext.startActivity(goNext);
                }
            });
        }
        else if(holder instanceof  HeaderViewHolder) {
            StoreCategoryItem curItem = items2.get(0);
            ((HeaderViewHolder) holder).imgRand.setImageResource(curItem.getIcon());
            ((HeaderViewHolder) holder).txtRand.setText(curItem.getCatTitle());
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
