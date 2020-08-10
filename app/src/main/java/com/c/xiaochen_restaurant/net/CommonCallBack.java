package com.c.xiaochen_restaurant.net;

import com.c.xiaochen_restaurant.utils.GsonUtil;
import com.google.gson.internal.$Gson$Types;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

public abstract class CommonCallBack<T> extends StringCallback {
    Type mtype;
    public CommonCallBack(){
        mtype=getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<? extends CommonCallBack> aClass) {
        Type superclass=aClass.getGenericSuperclass();
        if(superclass instanceof Class){
            throw new RuntimeException("Miss type parameter");
        }
        ParameterizedType parameterizedType= (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);

    }


    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    public abstract void onError(Exception e);
    public abstract void onSuccess(T response);

    @Override
    public void onResponse(String response, int id) {
        try {
            JSONObject resp=new JSONObject(response);
            int resultCode=resp.getInt("resultCode");
            if(resultCode==1){
                onSuccess((T) GsonUtil.getGson().fromJson(resp.getString("data"), mtype));
                //onSuccess((resp.getJSONObject("data")));

            }
            else {
                onError(new RuntimeException(resp.getString("resultMessage")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        }

    }
}
