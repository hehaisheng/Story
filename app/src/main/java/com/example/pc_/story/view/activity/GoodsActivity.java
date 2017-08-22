package com.example.pc_.story.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pc_.story.R;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.model.AllData;
import com.example.pc_.story.model.MeatModel;
import com.example.pc_.story.model.OrderModel;
import com.example.pc_.story.view.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class GoodsActivity extends BaseActivity {


    @BindView(R.id.goods_head_title)
    TextView goodsHeadTitle;
    @BindView(R.id.goods_recycler)
    RecyclerView goodsRecycler;
    @BindView(R.id.goods_all_money)
    TextView goodsAllMoney;


    public List<MeatModel> goodsStrings = new ArrayList<>();
    public List<OrderItem>  orderItems=new ArrayList<>();



    public GoodsAdapter goodsAdapter;
    public OrderModel orderModel;
    public OrderItem orderItem;
    public LiteOrmManager liteOrmManager;

    public int currentAllMoney;
    public boolean  hasSelect=false;

    @Override
    public int getLayout() {
        return R.layout.goods_activity;
    }

    @Override
    public void initView() {


        liteOrmManager=LiteOrmManager.newInstance(this);
        orderItems=liteOrmManager.getQueryAll(OrderItem.class);
        //还没有保存过购买的商品
        hasSelect = !(orderItems.size() == 0 || orderItems == null);
        if(!hasSelect){
            orderItem=new OrderItem();
        }else{
            boolean  allPay=true;
            for(int i=0;i<orderItems.size();i++){
                //如果没有支付
                if(!orderItems.get(i).isHasPay()){
                    orderItem=orderItems.get(i);
                    currentAllMoney= orderItem.getMoney();
                    goodsAllMoney.setText(String.valueOf(currentAllMoney));
                    allPay=false;
                }
            }
            if(allPay){
                orderItem=new OrderItem();
                //用来判断没有保存过
                hasSelect=false;
            }

        }

        goodsRecycler.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < AllData.meatStrings.length; i++) {
            int j=i%5;
            MeatModel meatModel = new MeatModel();
            meatModel.setMeatType(AllData.meatStrings[i]);
            meatModel.setMeatMoney(i + 10);
            meatModel.setShopName(AllData.shopStrings[j]);
            goodsStrings.add(meatModel);
        }
        goodsAdapter = new GoodsAdapter(R.layout.goods_item, goodsStrings);
        goodsRecycler.setAdapter(goodsAdapter);
        //都是添加到未支付中的，其中未支付只有一个
        goodsAdapter.setIAddGoodsPay(new GoodsAdapter.IAddGoodsPay() {
            @Override
            public void addPayMoney(int money) {
                currentAllMoney+=money;
                orderItem.setMoney(money);
                goodsAllMoney.setText(String.valueOf(currentAllMoney));
                if(!hasSelect){
                    liteOrmManager.save(orderItem);
                }else{
                    //每个item的id都不同，更加id来更新
                    liteOrmManager.update(orderItem);
                }


            }
            @Override
            public void addGoodsName(String goodsName) {
                orderItem.setOrderStrings(goodsName);
                //因为在调用该方法时，先调用上面的方法，那么已经保存过了，所以只需要更新
                //每个item的id都不同，更加id来更新
                liteOrmManager.update(orderItem);

            }

        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }



}
