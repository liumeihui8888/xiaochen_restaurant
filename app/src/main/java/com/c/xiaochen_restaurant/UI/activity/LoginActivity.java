package com.c.xiaochen_restaurant.UI.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.c.xiaochen_restaurant.R;
import com.c.xiaochen_restaurant.UserInfoHolder;
import com.c.xiaochen_restaurant.bean.User;
import com.c.xiaochen_restaurant.biz.UserBiz;
import com.c.xiaochen_restaurant.net.CommonCallBack;
import com.c.xiaochen_restaurant.utils.T;
//import com.c.xiaomei_restaurant.UserInfoHolder;
//import com.c.xiaomei_restaurant.bean.User;

import org.json.JSONException;
import org.json.JSONObject;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.cookie.CookieJarImpl;

public class LoginActivity extends Activity {
    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private UserBiz userBiz=new UserBiz();
    private String username01;
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_PASSWORD = "key_password";

//    @Override
//    protected void onResume() {
//        super.onResume();
//        CookieJarImpl cookieJar= (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
//        cookieJar.getCookieStore().removeAll();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("================",getClass().toString());
        initView();
        initIntent();
        initEvent();
    }

    private void initIntent() {
        Intent intent=getIntent();
        if(intent!=null){
            String username=intent.getStringExtra(KEY_USERNAME);
            if(username!=null){
                mEditUsername.setText(username);
            }
            String password=intent.getStringExtra(KEY_PASSWORD);
            if(password!=null){
                mEditPassword.setText(password);
            }
        }
    }

    private void initView() {
        mEditUsername=findViewById(R.id.id_ed_username);
        mEditPassword=findViewById(R.id.id_tv_password);
        mBtnLogin=findViewById(R.id.id_tv_login);
        mTvRegister=findViewById(R.id.id_tv_register);
    }

    private void initEvent(){

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=mEditUsername.getText().toString();
                String password=mEditPassword.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password))

                {
                    T.makeToast(LoginActivity.this,"账号999或密码不能为空");
                    return;
                }
               // startLoadingProgress();

                userBiz.login(username, password, new CommonCallBack<User>() {
                    @Override
                    public void onError(Exception e) {
                      //  stopLoadingProgress();

                        T.showToast(e.getMessage());

                    }

                    @Override
                    public void onSuccess(User response) {
                      //  stopLoadingProgress();
                        T.makeToast(LoginActivity.this,"登陆成功");

                        UserInfoHolder.getInstance().setUser(response);
                        toOrderActivity();
                        finish();
                    }
                });

            }
        });

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toResgisterActivity();
            }
        });
    }

    private void toResgisterActivity(){
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void toOrderActivity(){
        Intent intent=new Intent(this, OrderActivity.class);
        startActivity(intent);
        finish();

    }

    public static void launch(Context context,String username,String password){
        Intent intent=new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KEY_USERNAME,username);
        intent.putExtra(KEY_PASSWORD,password);
        context.startActivity(intent);
    }


}

