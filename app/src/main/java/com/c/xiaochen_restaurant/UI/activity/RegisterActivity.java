package com.c.xiaochen_restaurant.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.c.xiaochen_restaurant.R;
import com.c.xiaochen_restaurant.bean.User;
import com.c.xiaochen_restaurant.biz.UserBiz;
import com.c.xiaochen_restaurant.net.CommonCallBack;
import com.c.xiaochen_restaurant.utils.T;

public class RegisterActivity extends Activity {

    private EditText mEtusername;
    private EditText mEtpassword;
    private EditText mEtrepassword;
    private Button mBtnResgister;
    private UserBiz userBiz;

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        userBiz.onDestory();
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");
        userBiz=new UserBiz();
        initView();
        initEvent();



    }

    private void initView() {
        mEtusername=findViewById(R.id.id_et_username);
        mEtpassword=findViewById(R.id.id_et_password);
        mEtrepassword=findViewById(R.id.id_et_repassword);
        mBtnResgister=findViewById(R.id.id_btn_register);
    }

    private void initEvent(){

        mBtnResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=mEtusername.getText().toString();
                String password=mEtpassword.getText().toString();
                final String repassword=mEtrepassword.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password))
                {
                    T.makeToast(RegisterActivity.this,"账号888或密码不能为空");
                    return;
                }
                if(!password.equals(repassword)){
                    T.makeToast(RegisterActivity.this,"两次输入密码不一致");
                    return;
                }
               // startLoadingProgress();
                userBiz.register(username, password, new CommonCallBack<User>() {
                    @Override
                    public void onError(Exception e) {
                        T.makeToast(RegisterActivity.this,e.getMessage());
                        Log.d("eeee",e.getMessage());
                       // stopLoadingProgress();
                    }

                    @Override
                    public void onSuccess(User response) {
                        T.makeToast(RegisterActivity.this,"注册成功,用户名是："+response.getUsername());
                        LoginActivity.launch(RegisterActivity.this,response.getUsername(),response.getPassword());
                        finish();
                    }
                });


            }
        });
    }

}