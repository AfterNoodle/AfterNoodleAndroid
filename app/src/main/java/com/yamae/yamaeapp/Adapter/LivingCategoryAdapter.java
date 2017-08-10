package com.yamae.yamaeapp.Adapter;

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

import com.yamae.yamaeapp.Activity.LivingListActivity;
import com.yamae.yamaeapp.Item.LivingCategoryItem;
import com.yamae.yamaeapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-11.
 */

public class LivingCategoryAdapter extends RecyclerView.Adapter {
    List<LivingCategoryItem> mItem;
    Activity activity;
    Context mContext;
    Intent goNext;
    public LivingCategoryAdapter(List<LivingCategoryItem> mItem, Context mContext){
        this.mItem = mItem;
        activity = (Activity) mContext;
        this.mContext = mContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_living_category,null);

        // 거지같이 match parent가 안 되는 카드뷰 너비 지정 코드
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        view.setLayoutParams(new CardView.LayoutParams(width,CardView.LayoutParams.MATCH_PARENT));
        view.setLayoutParams(new CardView.LayoutParams(height,CardView.LayoutParams.WRAP_CONTENT));

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
                goNext.putExtra("Title",mItem.get(position).getTxtLivCatName());
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
