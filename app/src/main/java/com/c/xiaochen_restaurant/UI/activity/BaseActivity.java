package com.c.xiaochen_restaurant.UI.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.tu.loadingdialog.LoadingDialog;

//import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends Activity {
    private Dialog progressDialog;

    //  private SwipeRefreshLayout swipeRefreshLayout;
    private LoadingDialog.Builder loadBuilder=new LoadingDialog.Builder(this)
            .setMessage("加载中...")
            .setCancelable(true)
            .setCancelOutside(true);
    LoadingDialog dialog=loadBuilder.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面的顶部导航：就是网络信号，电量显示的导航栏
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(0xFF000000);
        }



    }

    protected void startLoadingProgress(){

        dialog.show();

    }
    protected void stopLoadingProgress(){
        if(dialog.isShowing()&&dialog!=null){
            dialog.dismiss();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        dialog=null;
    }

    protected void toLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
