package com.yamae.yamaeapp.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yamae.yamaeapp.Fragment.BookmarkFragment;
import com.yamae.yamaeapp.Fragment.LivingCategoryFragment;
import com.yamae.yamaeapp.Fragment.SettingFragment;
import com.yamae.yamaeapp.Fragment.StoreCategoryFragment;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.Java.ToolBar;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    selFragment = new StoreCategoryFragment();
                    toolbar.setTitle(R.string.title_store);
                    break;
                case R.id.navigation_living:
                    selFragment = new LivingCategoryFragment();
                    toolbar.setTitle(R.string.title_living);
                    break;
                case R.id.navigation_bookmark:
                    selFragment = new BookmarkFragment();
                    toolbar.setTitle(R.string.title_bookmark);
                    break;
                case R.id.navigation_setting:
                    selFragment = new SettingFragment();
                    toolbar.setTitle(R.string.title_setting);
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contentMain, selFragment);
            transaction.commit();
            return true;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_store);
        toolbar.setTitleTextColor(Color.WHITE);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // activity_main의 contentMain fragment의 초기값을 storeCategory로 초기화 시켜주기
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentMain,new StoreCategoryFragment());
        transaction.commit();


    }

}
