package com.example.pc_.story.database;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by pc- on 2017/8/14.
 */
@Table("GoodsJudgeItem")
public class GoodsJudgeItem {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;



    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Column("GoodsName")
    public String goodsName;

    public int getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(int judgeType) {
        this.judgeType = judgeType;
    }

    @Column("JudgeType")
    public int  judgeType;

    public boolean isHasJudge() {
        return hasJudge;
    }

    public void setHasJudge(boolean hasJudge) {
        this.hasJudge = hasJudge;
    }

    @Column("HasJudge")
    public boolean hasJudge;

}
