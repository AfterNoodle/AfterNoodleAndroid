package com.yamae.yamaeapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yamae.yamaeapp.fragment.BookmarkFragment;
import com.yamae.yamaeapp.fragment.LivingCategoryFragment;
import com.yamae.yamaeapp.fragment.SettingFragment;
import com.yamae.yamaeapp.fragment.StoreCategoryFragment;
import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.java.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Fragment selFragment = null;

//    Fragment moveFragment;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.navigation) BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mContext = this;

        init();


    }

    /**
     * view init method.
     */
    private void init() {
        // toolbar 설정
        setSupportActionBar(toolbar);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigationView);


        // activity_main의 contentMain fragment의 초기값을 storeCategory로 초기화 시켜주기
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentMain,new StoreCategoryFragment());
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    toolbar.setTitle("식사");
                    selFragment = new StoreCategoryFragment();
                    break;
                case R.id.navigation_living:
                    toolbar.setTitle("집");
                    selFragment = new LivingCategoryFragment();
                    break;
                case R.id.navigation_bookmark:
                    toolbar.setTitle("즐겨찾기");
                    selFragment = new BookmarkFragment();
                    break;
                case R.id.navigation_setting:
                    toolbar.setTitle("설정");
                    selFragment = new SettingFragment();
                    break;
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contentMain, selFragment);
            transaction.commit();
            return true;
        }

    };

}
