package com.c.xiaochen_restaurant.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.c.xiaochen_restaurant.R;

public class SplashActivity extends Activity {
    private Button mbtn;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_splash);
        initView();
        initEvent();
        handler.postDelayed(runnable,3000);
    }

    private void initEvent() {
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                toLoginActivity();
            }
        });
    }

    private void initView() {

        mbtn=findViewById(R.id.id_btn_skip1);
    }

    private void toLoginActivity(){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        Log.i("------------------",getClass().toString());
        finish();
    }

    protected void onDestory(){
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
