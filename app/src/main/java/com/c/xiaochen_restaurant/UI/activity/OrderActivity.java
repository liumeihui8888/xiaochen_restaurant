package com.c.xiaochen_restaurant.UI.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.c.xiaochen_restaurant.R;
import com.c.xiaochen_restaurant.UserInfoHolder;
import com.c.xiaochen_restaurant.bean.Order;
import com.c.xiaochen_restaurant.bean.User;
import com.c.xiaochen_restaurant.biz.OrderBiz;
import com.c.xiaochen_restaurant.net.CommonCallBack;
import com.c.xiaochen_restaurant.utils.T;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class OrderActivity extends Activity {
    private ImageView mIvIcon;
    private Button mBtnTakeOrder;
    private TextView mTvName;

    private RecyclerView mRecycerview;
    // private SwipeRefreshLayout mRefreshLayout;
    private com.c.xiaomei_restaurant.adapter.OrderAdapter mAdapter;
    private List<Order> mDatas=new ArrayList<>();
    private OrderBiz orderBiz=new OrderBiz();
    private int mCurrentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecycerview.setLayoutManager(linearLayoutManager);

        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startLoadingProgress();
        loadData();
    }

    private void loadData() {
        orderBiz.listByPage(0, new CommonCallBack<List<Order>>() {
            @Override
            public void onError(Exception e) {
               // stopLoadingProgress();
                T.makeToast(OrderActivity.this,e.getMessage());
                if(e.getMessage().contains("用户未登陆")){
                   // toLoginActivity();
                }

            }

            @Override
            public void onSuccess(List<Order> response) {
               // stopLoadingProgress();
                T.makeToast(OrderActivity.this,"订单更新成功");
                mDatas.clear();
                mDatas.addAll(response);
                mAdapter.notifyDataSetChanged();




            }
        });


    }

    private void initView(){
        mIvIcon=findViewById(R.id.id_iv_icon);
        mBtnTakeOrder=findViewById(R.id.id_btn_take_order);
        mTvName=findViewById(R.id.id_tv_name);
        mRecycerview=findViewById(R.id.id_recyclerview);
       //    mRefreshLayout=findViewById(R.id.id_refresh_layout);
        User user= UserInfoHolder.getInstance().getUser();
        if(user!=null){
            mTvName.setText(user.getUsername());
        }else{
            toLoginActivity();
            return;
        }

//        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadData();
//            }
//        });
//        mRefreshLayout.setOnPullUpRefreshListener(new SwipeRefreshLayout.OnPullUpRefreshListener() {
//            @Override
//            public void onPullUpRefresh() {
//                loadMore();
//            }
//        });
        mAdapter=new com.c.xiaomei_restaurant.adapter.OrderAdapter(this,mDatas);
     //   mRecycerview.setLayoutManager(new LinearLayoutManager(this));
      //  mRecycerview.setAdapter(mAdapter);
        Picasso.with(this).load(R.drawable.icon).placeholder(R.drawable.pictures_no).into(mIvIcon);

    }
    private void initEvent(){
        mBtnTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toProductListActivity();
            }
        });

    }

    private void toProductListActivity(){
        Intent intent=new Intent(this,ProductListActivity.class);
        startActivity(intent);
    }

    private void loadMore() {
        orderBiz.listByPage(++mCurrentPage, new CommonCallBack<List<Order>>() {
            @Override
            public void onError(Exception e) {
             //   stopLoadingProgress();
                T.makeToast(OrderActivity.this,e.getMessage());
                //       mRefreshLayout.setPullUpRefreshing(false);
                mCurrentPage--;
                if(e.getMessage().contains("用户未登陆")){
                    toLoginActivity();
                }
            }

            @Override
            public void onSuccess(List<Order> response) {
              //  stopLoadingProgress();
                if(response.size()==0){
                    T.makeToast(OrderActivity.this,"没有历史订单了~");
                    //       mRefreshLayout.setPullUpRefreshing(false);
                    return;
                }else {
                    T.makeToast(OrderActivity.this,"更新订单成功");
                    mDatas.addAll(response);
                    mAdapter.notifyDataSetChanged();
                    //          mRefreshLayout.setPullUpRefreshing(false);
                }

            }
        });
    }

    public void toLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }




}

