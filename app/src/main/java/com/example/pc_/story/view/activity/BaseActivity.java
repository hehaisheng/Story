package com.example.pc_.story.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pc- on 2017/5/21.
 */
public  abstract  class BaseActivity extends FragmentActivity {

    //各个类都需要用到的类或对象，在这里初始化
    public CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        compositeSubscription=new CompositeSubscription();
        initView();
        initData();
        initEvent();


    }


    public abstract  int getLayout();
    public abstract void initView();
    public  abstract void initData();
    public  abstract void initEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(compositeSubscription!=null){
            compositeSubscription.unsubscribe();
            compositeSubscription=null;
        }


    }
}
