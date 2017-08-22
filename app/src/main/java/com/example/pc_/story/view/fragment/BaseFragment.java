package com.example.pc_.story.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pc- on 2017/5/22.
 */
public abstract class BaseFragment extends Fragment {


    public Unbinder unbinder;
    public CompositeSubscription compositeSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder= ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        compositeSubscription=new CompositeSubscription();
        rxPreLoad();
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();


    }

    public abstract void  rxPreLoad();
    public abstract int getLayoutId();
    public abstract void initView();
    public  abstract void initData();
    public  abstract void initEvent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }

        if(compositeSubscription!=null){
            compositeSubscription.unsubscribe();
            compositeSubscription=null;
        }

    }
}
