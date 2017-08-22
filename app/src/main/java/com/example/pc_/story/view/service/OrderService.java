package com.example.pc_.story.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.pc_.story.database.LiteOrmManager;
import com.example.pc_.story.database.OrderItem;
import com.example.pc_.story.retrofit.ComSchedulers;
import com.example.pc_.story.retrofit.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by pc- on 2017/8/14.
 */
public class OrderService  extends Service {


    public RxBus rxBus;
    public LiteOrmManager liteOrmManager;
    public OrderItem orderItem;

    public int i=1;
    public String orderTime;
    //已经开启过线程的订单
    public List<OrderItem>  orderLists=new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        orderTime=intent.getStringExtra("orderTime");
        liteOrmManager=LiteOrmManager.newInstance(this);
        orderLists=liteOrmManager.getQueryAll(OrderItem.class);
        for(int i=0;i<orderLists.size();i++){
            if(orderTime.equals(orderLists.get(i).getPayTime())){
                orderItem=orderLists.get(i);
            }
        }
        //说明已经开启过，判断新开启的是否存在
        Observable.interval(1, TimeUnit.MINUTES,Schedulers.trampoline())
               .take(2)
               .compose(ComSchedulers.<Long>applyIoSchedulers())
               .subscribe(new Action1<Long>() {
                  @Override
                  public void call(Long  along) {

                      rxBus= RxBus.newInstance();
                      if(orderItem.getPayType()==1){
                          orderItem.setPayType(2);
                      }else if(orderItem.getPayType()==2){
                          orderItem.setPayType(3);
                      }
                      liteOrmManager.update(orderItem);
                      //对数据保存，并发送给fragment显示
                      rxBus.post(orderItem.getPayTime());


                  }
              });

        return super.onStartCommand(intent, flags, startId);


    }
}
