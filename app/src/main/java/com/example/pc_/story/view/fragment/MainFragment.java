package com.example.pc_.story.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.pc_.story.R;
import com.example.pc_.story.view.activity.GoodsActivity;
import com.example.pc_.story.view.activity.ShopActivity;
import com.example.pc_.story.view.adapter.MainAdapter;
import com.example.pc_.story.view.custom.CustomImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener{


    @BindView(R.id.main_greens)
    CustomImageView mainGreens;
    @BindView(R.id.main_cooked)
    CustomImageView mainCooked;
    @BindView(R.id.main_seaFood)
    CustomImageView mainSeaFood;
    @BindView(R.id.main_freeze)
    CustomImageView mainFreeze;
    @BindView(R.id.main_goods)
    CustomImageView mainGoods;
    @BindView(R.id.main_grains)
    CustomImageView mainGrains;
    @BindView(R.id.main_fruit)
    CustomImageView mainFruit;
    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.main_meat)
    CustomImageView mainMeat;
    public List<String>  mainStrings=new ArrayList<>();

    public MainAdapter mainAdapter;
    public Intent intent;

    @Override
    public void rxPreLoad() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {

        mainStrings.clear();
        mainRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        for(int i=0;i<10;i++){
            mainStrings.add("华辉肠粉"+i);
        }
        mainAdapter=new MainAdapter(R.layout.item_main,mainStrings);
        mainRecycler.setAdapter(mainAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mainMeat.setOnClickListener(this);
        mainRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                startToActivity(1);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_meat:
                startToActivity(0);
                break;
        }
    }
    public void startToActivity(int i){

        if(i==0){
            intent=new Intent(getActivity(), GoodsActivity.class);
            intent.putExtra("GoodsType","meat");
        }else if(i==1){
            intent=new Intent(getActivity(), ShopActivity.class);
            intent.putExtra("ShopName","HuaHui");
        }
        startActivity(intent);
    }
}
