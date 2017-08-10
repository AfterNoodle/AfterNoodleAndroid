package com.yamae.yamaeapp.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yamae.yamaeapp.Fragment.BookmarkFragment;
import com.yamae.yamaeapp.Fragment.LivingCategoryFragment;
import com.yamae.yamaeapp.Fragment.SettingFragment;
import com.yamae.yamaeapp.Fragment.StoreCategoryFragment;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.Java.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Toolbar toolbar;
    Fragment selFragment = null;
//    Fragment moveFragment;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    selFragment = new StoreCategoryFragment();
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
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentMain, selFragment);
            transaction.commit();
            return true;
        }

    };

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        moveFragment = selFragment;
//        super.onSaveInstanceState(outState);
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);

//        if (savedInstanceState != null) {
//           selFragment = moveFragment;
//
//        }


        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);

//        // toolbar 설정
//        toolbar = (Toolbar) findViewById(R.id.defaultToolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(R.string.title_store);
//        toolbar.setTitleTextColor(Color.WHITE);

        // activity_main의 contentMain fragment의 초기값을 storeCategory로 초기화 시켜주기
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentMain,new StoreCategoryFragment());
        transaction.commit();


    }

}
