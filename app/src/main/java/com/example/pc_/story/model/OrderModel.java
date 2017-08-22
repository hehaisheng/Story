package com.example.pc_.story.model;

/**
 * Created by pc- on 2017/8/13.
 */
public class OrderModel {

    public String getOrderStrings() {
        return orderStrings;
    }

    public void setOrderStrings(String orderStrings) {
        if(this.orderStrings==null){
            this.orderStrings=orderStrings;
        }else{
            this.orderStrings+= orderStrings;
        }

    }

    public String orderStrings;

    public int getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney += allMoney;
    }

    public int allMoney;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    //0代表没有支付，1代表即将送货，2代表已经在送货
    public int payType=0;


    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public  String payTime;

    public boolean isHasPay() {
        return hasPay;
    }

    public void setHasPay(boolean hasPay) {
        this.hasPay = hasPay;
    }

    public  boolean hasPay;

}
