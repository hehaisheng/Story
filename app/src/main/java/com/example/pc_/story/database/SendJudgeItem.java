package com.example.pc_.story.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/15.
 */
@Table("SendJudgeItem")
public class SendJudgeItem {



    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    @Column("SendName")
    public  String sendName;

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    @Column("OrderContent")
    public String orderContent;

    public int getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(int judgeType) {
        this.judgeType = judgeType;
    }

    @Column("JudgeType")
    public int judgeType;

    public boolean isHasJudge() {
        return hasJudge;
    }

    public void setHasJudge(boolean hasJudge) {
        this.hasJudge = hasJudge;
    }

    @Column("HasJudge")
    public boolean hasJudge;


}
