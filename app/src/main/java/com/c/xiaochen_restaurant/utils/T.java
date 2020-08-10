package com.c.xiaochen_restaurant.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class T {
    private static Toast toast;

    public static void showToast(String content) {

        Log.i("111111111111=========", content);
        toast.setText(content);
        toast.show();
        Log.i("333333333333333333333", content);

    }

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

    }

    public static void makeToast(final Context context, final String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();

    }

    public static void makeToast(final Context context, final String text, final int time) {
        Toast.makeText(context, text, time).show();

    }
}