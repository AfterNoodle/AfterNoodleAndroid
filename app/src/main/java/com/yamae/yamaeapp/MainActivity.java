package com.yamae.yamaeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    selFragment = new StoreCategoryFragment();
                    //nStoreCategoryFragment();

                    break;
                case R.id.navigation_living:
                    selFragment = new LivingCategoryFragment();
                    break;
                case R.id.navigation_bookmark:
                    selFragment = new BookmarkFragment();
                    break;
                case R.id.navigation_setting:
                    selFragment = new SettingFragment();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contentMain, selFragment);
            transaction.commit();
            return true;
        }

    };

    public RecyclerView nStoreCategoryFragment(){
        setContentView(R.layout.fragment_store_category);
        RecyclerView recStrCat = (RecyclerView) findViewById(R.id.listStoreCategory);
        return recStrCat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // activity_main의 contentMain fragment의 초기값을 storeCategory로 초기화 시켜주기
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentMain,new StoreCategoryFragment());
        transaction.commit();


    }

}
