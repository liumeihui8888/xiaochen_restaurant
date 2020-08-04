package com.c.xiaochen_restaurant.biz;

import com.c.xiaochen_restaurant.bean.User;
import com.c.xiaochen_restaurant.config.Config;
import com.c.xiaochen_restaurant.net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

public class UserBiz {
    public  void login(String username, String password, CommonCallBack<User> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"user_login")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);



    }
    public void register(String username,String password,CommonCallBack<User> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"user_register")
                .tag(this)
                .addParams("username",username)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);

    }
    public void onDestory(){
        OkHttpUtils.getInstance().cancelTag(this);
    }

}

