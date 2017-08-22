package com.example.pc_.story.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.story.R;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.utils.AutoSplit;
import com.example.pc_.story.view.service.OrderService;

import java.util.List;

/**
 * Created by pc- on 2017/8/13.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderItem> {

    public Context context;
    public LiteOrmManager liteOrmManager;

    public   String timeString;

    public OrderAdapter(Context context,int layoutResId, List<OrderItem> data){
        this(layoutResId,data);
        this.context=context;
        liteOrmManager=LiteOrmManager.newInstance(context);
    }
    public OrderAdapter(int layoutResId, List<OrderItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final OrderItem orderItem) {
        baseViewHolder.setText(R.id.order_shop_name, AutoSplit.autoSplit(orderItem.getOrderStrings()))
                .setText(R.id.order_shopping_money,"应付"+orderItem.getMoney()+"元  ")
        .setText(R.id.order_shop_time,orderItem.getPayTime());
        if(orderItem.isHasPay()){
            baseViewHolder.setText(R.id.order_pay,"已支付");
        }
        if(orderItem.getPayType()==1){
            baseViewHolder.setText(R.id.order_shopping_send,"即将派送");
        }else if(orderItem.getPayType()==2){
            baseViewHolder.setText(R.id.order_shopping_send,"正在派送");
        }else if(orderItem.getPayType()==3){
            baseViewHolder.setText(R.id.order_shopping_send,"派送完成");
        }


        final Button  payButton=((Button) baseViewHolder.getView(R.id.order_pay));
        final TextView  shoppingType=(TextView) baseViewHolder.getView(R.id.order_shopping_send);
        final TextView payTimeView=baseViewHolder.getView(R.id.order_shop_time);
        payButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

               if(orderItem.isHasPay()){
                  //已经支付过
               }else{
                  long time=System.currentTimeMillis();
                   orderItem.setPayTime(String.valueOf(time));
                   orderItem.setHasPay(true);
                   if(orderItem.isHasPay()){
                       payButton.setText("已支付");
                   }
                   payTimeView.setText(String.valueOf(time));
                   if(orderItem.getPayType()==0){
                       //即将派送
                       orderItem.setPayType(1);
                       shoppingType.setText("即将派送");

                   }
                   liteOrmManager.update(orderItem);
                   //notifyDataSetChanged();
                   Intent intent=new Intent(context, OrderService.class);
                   intent.putExtra("orderTime",orderItem.getPayTime());
                   context.startService(intent);
               }


            }
        });
    }
}
