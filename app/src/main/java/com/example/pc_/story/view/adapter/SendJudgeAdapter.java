package com.example.pc_.story.view.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.story.R;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.SendJudgeItem;

import java.util.List;

/**
 * Created by pc- on 2017/8/15.
 */
public class SendJudgeAdapter extends BaseQuickAdapter<SendJudgeItem> {

    public Context context;
    public LiteOrmManager liteOrmManager;
    public SendJudgeAdapter(int layoutResId, List<SendJudgeItem> data) {
        super(layoutResId, data);
    }

    public SendJudgeAdapter(Context context,int layoutResId, List<SendJudgeItem> data) {
        this(layoutResId, data);
        this.context=context;
        liteOrmManager=LiteOrmManager.newInstance(context);
    }
    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final SendJudgeItem sendJudgeItem) {

        baseViewHolder.setText(R.id.send_judge_send,sendJudgeItem.getSendName())
                .setText(R.id.send_judge_order,sendJudgeItem.getOrderContent());


        baseViewHolder.getView(R.id.send_judge_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseViewHolder.setBackgroundRes(R.id.send_judge_one,R.drawable.ic_play);
                sendJudgeItem.setHasJudge(true);
                sendJudgeItem.setJudgeType(1);
                liteOrmManager.update(sendJudgeItem,3);

            }
        });

        baseViewHolder.getView(R.id.send_judge_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseViewHolder.setBackgroundRes(R.id.send_judge_one,R.drawable.ic_play);
                sendJudgeItem.setHasJudge(true);
                sendJudgeItem.setJudgeType(2);
                liteOrmManager.update(sendJudgeItem,3);
            }
        });
        baseViewHolder.getView(R.id.send_judge_middle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        baseViewHolder.getView(R.id.send_judge_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseViewHolder.setBackgroundRes(R.id.send_judge_one,R.drawable.ic_play);
                sendJudgeItem.setHasJudge(true);
                sendJudgeItem.setJudgeType(3);
                liteOrmManager.update(sendJudgeItem,3);
            }
        });
        baseViewHolder.getView(R.id.send_judge_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseViewHolder.setBackgroundRes(R.id.send_judge_one,R.drawable.ic_play);
                sendJudgeItem.setHasJudge(true);
                sendJudgeItem.setJudgeType(4);
                liteOrmManager.update(sendJudgeItem,3);
            }
        });

        baseViewHolder.getView(R.id.send_judge_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseViewHolder.setBackgroundRes(R.id.send_judge_one,R.drawable.ic_play);
                sendJudgeItem.setHasJudge(true);
                sendJudgeItem.setJudgeType(5);
                liteOrmManager.update(sendJudgeItem,3);
            }
        });
    }
}
