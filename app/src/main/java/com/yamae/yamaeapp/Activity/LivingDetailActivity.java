package com.yamae.yamaeapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import com.yamae.yamaeapp.R;

/**
 * Created by HyunWook Kim on 2017-07-24.
 */

public class LivingDetailActivity extends AppCompatActivity {
    Intent intent = getIntent();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_detail);
        CollapsingToolbarLayout ct = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }
}
