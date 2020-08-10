package com.c.xiaochen_restaurant.UI.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.c.xiaochen_restaurant.R;
import com.c.xiaochen_restaurant.bean.Order;
import com.c.xiaochen_restaurant.config.Config;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OrderDetailActivity extends BaseActivity {
    private static final String KEY_ORDER="key_order";
    private ImageView mIvIcon;
    private TextView mTvTitle;
    private TextView mTvDesc;
    private TextView mTvPrice;
    private Order mOrder;


    public static void launch(Context context, Order order){
        Intent intent=new Intent(context,OrderDetailActivity.class);
        intent.putExtra(KEY_ORDER,order);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setTitle("订单详情");
        initView();
        Picasso.with(this)
                .load(Config.baseUrl+mOrder.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(mIvIcon);
        mTvTitle.setText(mOrder.getRestaurant().getName());
        mTvPrice.setText("共消费："+mOrder.getRestaurant().getPrice()+"元");
        List<Order.ProductVo> ps=mOrder.ps;
        StringBuffer sb=new StringBuffer();
        for(Order.ProductVo s:ps){
            sb.append(s.product.getName()).append("*").append(s.count).append("\n");

        }
        mTvDesc.setText(sb.toString());


    }

    private void initView() {
        mIvIcon=findViewById(R.id.id_iv_icon);
        mTvTitle=findViewById(R.id.id_tv_title);
        mTvDesc=findViewById(R.id.id_tv_desc);
        mTvPrice=findViewById(R.id.id_tv_price);
    }
}

