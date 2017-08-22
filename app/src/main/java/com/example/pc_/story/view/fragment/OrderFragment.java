package com.example.pc_.story.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pc_.story.R;
import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.presenter.IUpdateOrder;
import com.example.pc_.story.retrofit.RxBus;
import com.example.pc_.story.view.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class OrderFragment extends BaseFragment implements IUpdateOrder{

    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;


    //Fragment在左右滑动时，不会更新
    //当fragment所在的activity重新可见时，fragment会调用onActivityCreated

    public List<OrderItem>   orderItems=new ArrayList<>();


    public OrderAdapter orderAdapter;
    public LiteOrmManager liteOrmManager;
    public OrderItem orderItem;
    public RxBus rxBus;



    @Override
    public void rxPreLoad() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.order_fragment;
    }

    @Override
    public void initView() {


        rxBus=RxBus.newInstance();
        liteOrmManager=LiteOrmManager.newInstance(getActivity());
        orderRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initData() {


    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();


        orderItems=liteOrmManager.getQueryAll(OrderItem.class);
        if(orderItems.size()!=0){
                orderAdapter=new OrderAdapter(getActivity(),R.layout.order_item,orderItems);
                orderRecycler.setAdapter(orderAdapter);
        }


    }

    @Override
    public void update() {


        orderItems=liteOrmManager.getQueryAll(OrderItem.class);
        orderAdapter=new OrderAdapter(getActivity(),R.layout.order_item,orderItems);
        orderRecycler.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
    }
}
