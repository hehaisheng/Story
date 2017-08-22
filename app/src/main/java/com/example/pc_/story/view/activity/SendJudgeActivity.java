package com.example.pc_.story.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pc_.story.R;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.database.SendJudgeItem;
import com.example.pc_.story.utils.AutoSplit;
import com.example.pc_.story.view.adapter.SendJudgeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/15.
 */
public class SendJudgeActivity extends BaseActivity {

    @BindView(R.id.send_judge_header)
    TextView sendJudgeHeader;
    @BindView(R.id.send_judge_recycler)
    RecyclerView sendJudgeRecycler;

    public List<OrderItem> orderItemList=new ArrayList<>();
    public List<SendJudgeItem>   sendJudgeItems=new ArrayList<>();
    public List<SendJudgeItem>   noJudgeItems=new ArrayList<>();


    public SendJudgeAdapter sendJudgeAdapter;
    public LiteOrmManager liteOrmManager;


    @Override
    public int getLayout() {
        return R.layout.send_judge_activity;
    }

    @Override
    public void initView() {

        sendJudgeRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {

        liteOrmManager=LiteOrmManager.newInstance(this);
        sendJudgeItems=liteOrmManager.getQueryAll(SendJudgeItem.class);
        if(sendJudgeItems.size()==0||sendJudgeItems==null) {
            orderItemList = liteOrmManager.getQueryAll(OrderItem.class);
            for (int i = 0; i < orderItemList.size(); i++) {
                OrderItem orderItem = orderItemList.get(i);
                if (orderItem.getPayType() == 3) {

                    SendJudgeItem sendJudgeItem = new SendJudgeItem();
                    sendJudgeItem.setSendName("订单: "+orderItem.getPayTime());
                    sendJudgeItem.setOrderContent(AutoSplit.autoSplit(orderItem.getOrderStrings()));
                    liteOrmManager.save(sendJudgeItem, 3);
                    sendJudgeItems.add(sendJudgeItem);
                }
            }
            if (sendJudgeItems.size() != 0) {
                sendJudgeAdapter = new SendJudgeAdapter(this, R.layout.send_judge_item, sendJudgeItems);
            }

        }else{
            //如果已经存在，那么可能已经评价过
            for(int i=0;i<sendJudgeItems.size();i++){
                SendJudgeItem sendJudgeItem=sendJudgeItems.get(i);
                if(!sendJudgeItem.isHasJudge()){
                    noJudgeItems.add(sendJudgeItem);
                }
            }
            sendJudgeAdapter=new SendJudgeAdapter(this,R.layout.send_judge_item,noJudgeItems);
        }
        sendJudgeRecycler.setAdapter(sendJudgeAdapter);


    }

    @Override
    public void initEvent() {

    }


}
