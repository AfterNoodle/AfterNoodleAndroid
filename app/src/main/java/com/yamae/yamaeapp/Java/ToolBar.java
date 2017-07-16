package com.yamae.yamaeapp.Java;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yamae.yamaeapp.R;

/**
 * Created by HyunWook Kim on 2017-07-16.
 */

public class ToolBar {


    public ToolBar(AppCompatActivity acName, int layoutName, String tbTitle, int Type){

        acName.setContentView(layoutName);
        Toolbar toolbar = (Toolbar) acName.findViewById(R.id.defaultToolbar);
        acName.setSupportActionBar(toolbar);
        toolbar.setTitle(tbTitle);
    }
}
