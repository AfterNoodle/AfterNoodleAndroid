package com.yamae.yamaeapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.yamae.yamaeapp.R;
import com.yamae.yamaeapp.activity.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HyunWook Kim on 2017-07-07.
 */

public class SettingFragment extends Fragment {

    @BindView(R.id.img_profile) ImageView img_profile;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.img_profile)
    void profileClick(){
        getActivity().startActivity(new Intent(getActivity(), SignInActivity.class));
    }
}
