package com.yamae.yamaeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yamae.yamaeapp.activity.LivingDetailActivity;
import com.yamae.yamaeapp.item.LivingListItem;
import com.yamae.yamaeapp.java.RateImage;
import com.yamae.yamaeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */


public class LivingListAdapter extends RecyclerView.Adapter{

    Context mContext;
    List<LivingListItem> items;
    Intent goNext;

    public LivingListAdapter(List<LivingListItem> items, Context mContext){ //생성자
        this.items= items;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_living_list,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        RateImage ri = new RateImage();     // int 타입의 변수 rate 값을 받으면 그 값에 맞게 이미지를 내보내 주는 클래스
        final double rate;
        rate = items.get(position).getRate() * 0.1;
        ((ViewHolder)holder).txtLivTitle.setText(items.get(position).getReviewTitle());
        ((ViewHolder)holder).txtLivWriter.setText(items.get(position).getReviewWriter());
        ((ViewHolder)holder).txtRate.setText(rate+"");
        ((ViewHolder)holder).imgRate.setImageResource(ri.getRateColorImage(items.get(position).getRate()));
        ((ViewHolder)holder).itemLiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext = new Intent(mContext,LivingDetailActivity.class);
                goNext.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                goNext.putExtra("Title",items.get(position).getReviewTitle());
                goNext.putExtra("Writer",items.get(position).getReviewWriter());
                goNext.putExtra("Rate",rate);
                mContext.startActivity(goNext);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtLivTitle) TextView txtLivTitle;
        @BindView(R.id.txtLivWriter) TextView txtLivWriter;
        @BindView(R.id.txtRate) TextView txtRate;
        @BindView(R.id.imgFiveStar) ImageView imgRate;
        @BindView(R.id.itemLiv) LinearLayout itemLiv;
        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {return items.size(); }

}
