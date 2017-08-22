package com.example.pc_.story.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.pc_.story.R;
import com.example.pc_.story.view.adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc- on 2017/8/13.
 */
public class ShopActivity extends BaseActivity {


    @BindView(R.id.shop_head_title)
    TextView shopHeadTitle;
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;

    public List<String> goodsStrings=new ArrayList<>();

    public ShopAdapter shopAdapter;

    @Override
    public int getLayout() {
        return R.layout.shop_activity;
    }

    @Override
    public void initView() {

        shopRecycler.setLayoutManager(new LinearLayoutManager(this));
        for(int i=1;i<10;i++){
            goodsStrings.add(i+"");
        }
        shopAdapter=new ShopAdapter(R.layout.shop_item,goodsStrings);
        shopRecycler.setAdapter(shopAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
