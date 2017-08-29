package com.yamae.yamaeapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.view_user) FrameLayout userView;
    @BindView(R.id.view_logout) LinearLayout logoutView;

    FirebaseAuth mAuth;
    FirebaseUser curUser;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        curUser =  mAuth.getCurrentUser();
        if(curUser!=null){      //로그인 되어있을 때
            logoutView.setVisibility(View.VISIBLE);
            txtName.setText(curUser.getEmail());

        }else {     //로그인안되어 있을 때
            txtName.setText("로그인을 해주세요.");
            logoutView.setVisibility(View.GONE);
        }


    }

    @OnClick(R.id.view_user)
    void profileClick(){
        if(curUser ==null)
            getActivity().startActivity(new Intent(getActivity(), SignInActivity.class));
    }
    @OnClick(R.id.view_logout)
    void onLogoutClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("로그아웃 하시겠습니까");
// Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mAuth.signOut();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

// Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
