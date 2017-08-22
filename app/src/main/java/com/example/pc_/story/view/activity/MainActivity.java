package com.example.pc_.story.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.pc_.story.R;
import com.example.pc_.story.retrofit.ComSchedulers;
import com.example.pc_.story.retrofit.RxBus;
import com.example.pc_.story.view.fragment.MainFragment;
import com.example.pc_.story.view.fragment.OrderFragment;
import com.example.pc_.story.view.fragment.SelfFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by pc- on 2017/8/13.
 */
public class MainActivity extends BaseActivity {


    public ViewPager viewPager;

    public String[] titles={"首页","订单","我"};
    public List<Fragment> fragmentList=new ArrayList<>();

    public TabLayout tabLayout;
    public MainFragment mainFragment;
    public OrderFragment orderFragment;
    public SelfFragment selfFragment;
    public RxBus rxBus;
    public Subscription subscription;
    public SharedPreferences sharedPreferences;



    public boolean isLogin;




    @Override
    public int getLayout() {
        return R.layout.main_activity;
    }

    @Override
    public void initView() {

        sharedPreferences=getSharedPreferences("Login",0);
        isLogin=sharedPreferences.getBoolean("HasLogin",false);
        if(!isLogin){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        //有登录的时候，就执行下面的
        rxBus=RxBus.newInstance();
        viewPager=(ViewPager) findViewById(R.id.main_content);
        fragmentList.clear();
        mainFragment=new MainFragment();
        orderFragment=new OrderFragment();
        selfFragment=new SelfFragment();


        fragmentList.add(mainFragment);
        fragmentList.add(orderFragment);
        fragmentList.add(selfFragment);
        tabLayout=(TabLayout)  findViewById(R.id.art_tab);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return  fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

    }

    @Override
    public void initData() {

        subscription=rxBus.tObservable(String.class)
                .compose(ComSchedulers.<String>applyIoSchedulers())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        orderFragment.update();
                    }
                });
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fixInputMethodManagerLeak(this);
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
            subscription=null;
        }
    }

    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String [] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0;i < arr.length;i ++) {
            String param = arr[i];
            try{
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
}
