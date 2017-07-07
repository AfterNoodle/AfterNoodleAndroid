package com.yamae.yamaeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    selFragment = StoreCategoryFragment.newInstance();
                    break;
                case R.id.navigation_living:
                    selFragment = LivingCategoryFragment.newInstance();
                    break;
                case R.id.navigation_bookmark:
                    selFragment = BookmarkFragment.newInstance();
                    break;
                case R.id.navigation_setting:
                    selFragment = SettingFragment.newInstance();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // activity_main의 contentMain fragment의 초기값을 storeCategory로 초기화 시켜주기
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentMain, StoreCategoryFragment.newInstance());
        transaction.commit();
    }

}
