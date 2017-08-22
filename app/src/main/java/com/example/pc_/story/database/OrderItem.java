package com.example.pc_.story.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/14.
 */
@Table("OrderItem")
public class OrderItem {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;


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

    @Column("OrderStrings")
    public String  orderStrings;

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    @Column("PayTime")
    public String  payTime="0";

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money+= money;
    }

    @Column("PayMoney")
    public int money;

    public boolean isHasPay() {
        return hasPay;
    }

    public void setHasPay(boolean hasPay) {
        this.hasPay = hasPay;
    }

    @Column("HasPay")
    public boolean hasPay=false;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    @Column("PayType")
    public int payType=0;


}
