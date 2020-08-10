package com.c.xiaomei_restaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c.xiaochen_restaurant.R;
import com.c.xiaochen_restaurant.UI.activity.OrderDetailActivity;
import com.c.xiaochen_restaurant.bean.Order;
import com.c.xiaochen_restaurant.config.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemViewHolder> {
    private Context mcontext;
    private LayoutInflater mlayoutInflater;
    private List<Order> mDatas;

    public OrderAdapter(Context context,List<Order> datas){
        mcontext=context;
        mDatas=datas;
        mlayoutInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mlayoutInflater.inflate(R.layout.item_order,parent,false);
        return new OrderItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        Order order=mDatas.get(position);
        Picasso.with(mcontext)
                .load(Config.baseUrl+order.getRestaurant().getIcon())
                .placeholder(R.drawable.pictures_no)
                .into(holder.mIvImage);
        holder.mTvName.setText(order.getRestaurant().getName());
        if(order.ps.size()>0){
            holder.mTvLabel.setText(order.ps.get(0).product.getName()+"等"+order.getCount()+"件商品");
            holder.mTvPrice.setText("共消费："+order.getPrice()+"元");
        }



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView mIvImage;
        public TextView mTvName;
        public TextView mTvLabel;
        public TextView mTvPrice;


        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                    OrderDetailActivity.launch(mcontext,mDatas.get(getAdapterPosition()));

                }
            });
            mIvImage=itemView.findViewById(R.id.id_iv_image);
            mTvName=itemView.findViewById(R.id.id_tv_name);
            mTvLabel=itemView.findViewById(R.id.id_tv_label);
            mTvPrice=itemView.findViewById(R.id.id_tv_price);

        }
    }
}

