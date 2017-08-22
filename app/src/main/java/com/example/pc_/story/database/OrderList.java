package com.example.pc_.story.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/14.
 */
@Table("OrderItem")
public class OrderList {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Column("OrderTime")
    public String orderTime;

}
