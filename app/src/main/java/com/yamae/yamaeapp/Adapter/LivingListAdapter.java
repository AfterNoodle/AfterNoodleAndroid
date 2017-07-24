package com.yamae.yamaeapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamae.yamaeapp.Item.LivingListItem;
import com.yamae.yamaeapp.Java.RateImage;
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
    Activity activity;

    public LivingListAdapter(List<LivingListItem> items, Context mContext){ //생성자
        this.items= items;
        this.mContext = mContext;
        activity = (Activity)mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_living_list,null);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        RateImage ri = new RateImage();     // int 타입의 변수 rate 값을 받으면 그 값에 맞게 이미지를 내보내 주는 클래스
        double rate;
        rate = items.get(position).getRate() * 0.1;
        ((ViewHolder)holder).txtLivTitle.setText(items.get(position).getReviewTitle());
        ((ViewHolder)holder).txtLivWriter.setText(items.get(position).getReviewWriter());
        ((ViewHolder)holder).txtRate.setText(rate+"");
        ((ViewHolder)holder).imgRate.setImageResource(ri.getRateColorImage(items.get(position).getRate()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtLivTitle) TextView txtLivTitle;
        @BindView(R.id.txtLivWriter) TextView txtLivWriter;
        @BindView(R.id.txtRate) TextView txtRate;
        @BindView(R.id.imgFiveStar) ImageView imgRate;
        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {return items.size(); }

}
