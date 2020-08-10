package com.c.xiaochen_restaurant.biz;

import com.c.xiaochen_restaurant.bean.Order;
import com.c.xiaochen_restaurant.bean.Product;
import com.c.xiaochen_restaurant.config.Config;
import com.c.xiaochen_restaurant.net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;
import java.util.Map;

public class OrderBiz {
    public void listByPage(int currentPage, CommonCallBack<List<Order>> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"order_find")
                .tag(this)
                .addParams("currentPage",currentPage+"")
                .build()
                .execute(commonCallBack);
    }

    public void add(Order order,CommonCallBack commonCallBack){
        Map<Product,Integer> productIntegerMap=order.productIntegerMap;
        StringBuilder sb=new StringBuilder();
        for(Product p:productIntegerMap.keySet()){
            sb.append((p.getId())+"_"+productIntegerMap.get(p));
            sb.append("|");
        }
        sb=sb.deleteCharAt(sb.length()-1);

        OkHttpUtils.post()
                .url(Config.baseUrl+"order_add")
                .addParams("res_id",order.getRestaurant().getId()+ "")
                .addParams("product_str",sb.toString())
                .addParams("count",order.getCount()+ "")
                .addParams("price",order.getPrice()+ "")
                .tag(this)
                .build()
                .execute(commonCallBack);

    }
}

