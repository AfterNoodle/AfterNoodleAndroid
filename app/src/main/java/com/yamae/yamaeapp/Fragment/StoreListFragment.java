package com.yamae.yamaeapp.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yamae.yamaeapp.Adapter.StoreListAdapter;
import com.yamae.yamaeapp.Item.StoreCategoryItem;
import com.yamae.yamaeapp.Item.StoreListItem;
import com.yamae.yamaeapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-13.
 */

public class StoreListFragment extends Fragment {

    List<StoreListItem> items;
    List<StoreCategoryItem> items2;
    Context mContext;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listStore);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        items2 = new ArrayList<>();
        items2.add(new StoreCategoryItem(R.mipmap.logo_rounded,"랜덤"));

        items = new ArrayList<>();
        items.add(new StoreListItem(R.mipmap.ic_dice_c192,"랜덤"," "));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"BHC","맛은 있다만 양도 가격도 창렬!"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"1","맛은 있다만 양도 가격도 창렬!"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"22","맛은 있다만 양도 가격도"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"333","맛은 있다만 양도"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"4444","맛은 있다만"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"55555","맛은"));
        items.add(new StoreListItem(R.mipmap.logo_rounded,R.mipmap.ic_bookmark_c192,"666666","맛!"));


        recyclerView.setAdapter(new StoreListAdapter(items,items2,mContext));
        return view;
    }
}
