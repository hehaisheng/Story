package com.example.pc_.story.view;

import android.app.Application;

import com.example.pc_.story.model.OrderModel;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc- on 2017/8/13.
 */
public class MyApplication extends Application {

    //购买的全部价格
    public int getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney += allMoney;
    }

    public int allMoney;

    //商品名字和重量的连接
    public String getGoodsString() {
        return goodsString;
    }

    public void setGoodsString(String goodsString) {

        if(this.goodsString==null){
            this.goodsString=goodsString;
        }else{
            this.goodsString += goodsString;
        }

    }

    public String goodsString;

    //订单
    public List<OrderModel> getOrderModelList() {
        return orderModelList;
    }

    public void setOrderModelList(List<OrderModel> orderModelList) {
        this.orderModelList = orderModelList;
    }

    public List<OrderModel> orderModelList=new ArrayList<>();

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int payType;


    public static MyApplication myApplication;
    public static MyApplication newInstance() {

        if(myApplication==null){
            myApplication=new MyApplication();
        }
        return  myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
