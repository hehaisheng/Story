package com.example.pc_.story.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pc_.story.R;
import com.example.pc_.story.database.GoodsJudgeItem;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.view.adapter.GoodsJudgeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/14.
 */
public class GoodsJudgeActivity extends BaseActivity {

    @BindView(R.id.goods_judge_header)
    TextView goodsJudgeHeader;
    @BindView(R.id.goods_judge_recycler)
    RecyclerView goodsJudgeRecycler;

    public List<OrderItem> orderItemList=new ArrayList<>();
    public List<GoodsJudgeItem>   goodsJudgeItems=new ArrayList<>();
    public List<GoodsJudgeItem>   noJudgeItems=new ArrayList<>();


    public GoodsJudgeAdapter  goodsJudgeAdapter;
    public LiteOrmManager liteOrmManager;

    @Override
    public int getLayout() {
        return R.layout.goods_judge_activity;
    }

    @Override
    public void initView() {

        goodsJudgeRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {
        liteOrmManager=LiteOrmManager.newInstance(this);
        goodsJudgeItems=liteOrmManager.getQueryAll(GoodsJudgeItem.class);
        if(goodsJudgeItems.size()==0||goodsJudgeItems==null){
            orderItemList=liteOrmManager.getQueryAll(OrderItem.class);
            for(int i=0;i<orderItemList.size();i++){
                OrderItem orderItem=orderItemList.get(i);
                if(orderItem.getPayType()==3){
                    String[]  orderStrings=orderItem.getOrderStrings().split("@");
                    for (String orderString : orderStrings) {
                        GoodsJudgeItem goodsJudgeItem = new GoodsJudgeItem();
                        goodsJudgeItem.setGoodsName(orderString);
                        liteOrmManager.save(goodsJudgeItem, 2);
                        goodsJudgeItems.add(goodsJudgeItem);
                    }
                }

            }
            if(goodsJudgeItems.size()!=0){
                goodsJudgeAdapter=new GoodsJudgeAdapter(this,R.layout.goods_judge_item,goodsJudgeItems);
            }

        }else{
            //如果已经存在，那么可能已经评价过
            for(int i=0;i<goodsJudgeItems.size();i++){
                GoodsJudgeItem goodsJudgeItem=goodsJudgeItems.get(i);
                if(!goodsJudgeItem.isHasJudge()){
                    noJudgeItems.add(goodsJudgeItem);
                }
            }
            goodsJudgeAdapter=new GoodsJudgeAdapter(this,R.layout.goods_judge_item,noJudgeItems);
        }
        goodsJudgeRecycler.setAdapter(goodsJudgeAdapter);


    }

    @Override
    public void initEvent() {

    }


}
