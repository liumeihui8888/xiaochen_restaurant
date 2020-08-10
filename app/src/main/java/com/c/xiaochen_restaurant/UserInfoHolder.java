package com.c.xiaochen_restaurant;

import android.text.TextUtils;

import com.c.xiaochen_restaurant.bean.User;
import com.c.xiaochen_restaurant.utils.SPUtils;

public class UserInfoHolder {

    private static UserInfoHolder mInstance = new UserInfoHolder();
    private User mUser;

    private static final String KEY_USERNAME = "key_username";


    public static UserInfoHolder getInstance() {
        return mInstance;
    }

    public void setUser(User user) {
        mUser = user;
        if (user != null) {

            SPUtils.getInstance().put(KEY_USERNAME, user.getUsername());
        }
    }

    public User getUser() {

        User u = mUser;
        if (u == null) {
            String username = (String) SPUtils.getInstance().get(KEY_USERNAME, "");
            if (!TextUtils.isEmpty(username)) {
                u = new User();
                u.setUsername(username);
            }
        }
        mUser = u;
        return mUser;
    }
}

