package com.yamae.yamaeapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yamae.yamaeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    Intent getIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_detail);
        ButterKnife.bind(this);

        getIntent = getIntent();

        setToolbar();


        CollapsingToolbarLayout ct = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    /**
     * toolbar 설정하는 메소드
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent.getStringExtra("title"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
