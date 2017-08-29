package com.yamae.yamaeapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.yamae.yamaeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by songmho on 2017. 8. 29..
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.edit_id) EditText editId;
    @BindView(R.id.edit_pw) EditText editPw;
    @BindView(R.id.edit_name) EditText editName;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        editId.setHint("asdfasdf");
    }

    @OnClick(R.id.btn_signup)
    void onBtnSignUp(){
        Log.e(editId.getText().toString(),editPw.getText().toString());
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(editId.getText().toString(),editPw.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "회원가입을 성공하지 못했습니다. 다시 한 번 확인하세요", Toast.LENGTH_SHORT).show();
                        }else {
                            finish();
                        }
                    }
                });
    }
}

