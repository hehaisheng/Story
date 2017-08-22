package com.example.pc_.story.view.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.story.R;
import com.example.pc_.story.database.GoodsJudgeItem;
import com.example.pc_.story.database.LiteOrmManager;

import java.util.List;

/**
 * Created by pc- on 2017/8/14.
 */
public class GoodsJudgeAdapter  extends BaseQuickAdapter<GoodsJudgeItem> {


    public Context context;
    public LiteOrmManager liteOrmManager;

    public GoodsJudgeAdapter(int layoutResId, List<GoodsJudgeItem> data) {
        super(layoutResId, data);
    }

    public GoodsJudgeAdapter(Context context, int layoutResId, List<GoodsJudgeItem> data) {
        this(layoutResId, data);
        this.context=context;
        liteOrmManager=LiteOrmManager.newInstance(this.context);
    }
    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final GoodsJudgeItem goodsJudgeItem) {

        baseViewHolder.setText(R.id.goods_judge_goods,goodsJudgeItem.getGoodsName());
        baseViewHolder.getView(R.id.goods_judge_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!goodsJudgeItem.isHasJudge()){
                    baseViewHolder.setBackgroundRes(R.id.goods_judge_one,R.drawable.ic_play);
                    goodsJudgeItem.setJudgeType(1);
                    goodsJudgeItem.setHasJudge(true);
                    liteOrmManager.update(goodsJudgeItem,2);
                }


            }
        });
        baseViewHolder.getView(R.id.goods_judge_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!goodsJudgeItem.isHasJudge()){
                    baseViewHolder.setBackgroundRes(R.id.goods_judge_two,R.drawable.ic_play);
                    goodsJudgeItem.setJudgeType(1);
                    goodsJudgeItem.setHasJudge(true);
                    liteOrmManager.update(goodsJudgeItem,2);
                }

            }
        });
        baseViewHolder.getView(R.id.goods_judge_middle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!goodsJudgeItem.isHasJudge()){
                    baseViewHolder.setBackgroundRes(R.id.goods_judge_middle,R.drawable.ic_play);
                    goodsJudgeItem.setJudgeType(1);
                    goodsJudgeItem.setHasJudge(true);
                    liteOrmManager.update(goodsJudgeItem,2);

                }

            }
        });
        baseViewHolder.getView(R.id.goods_judge_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!goodsJudgeItem.isHasJudge()){
                    baseViewHolder.setBackgroundRes(R.id.goods_judge_four,R.drawable.ic_play);
                    goodsJudgeItem.setJudgeType(1);
                    goodsJudgeItem.setHasJudge(true);
                    liteOrmManager.update(goodsJudgeItem,2);
                }

            }
        });
        baseViewHolder.getView(R.id.goods_judge_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!goodsJudgeItem.isHasJudge()){
                    baseViewHolder.setBackgroundRes(R.id.goods_judge_five,R.drawable.ic_play);
                    goodsJudgeItem.setJudgeType(1);
                    goodsJudgeItem.setHasJudge(true);
                    liteOrmManager.update(goodsJudgeItem,2);

                }

            }
        });




    }


}
