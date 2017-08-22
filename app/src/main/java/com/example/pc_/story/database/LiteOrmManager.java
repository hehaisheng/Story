package com.example.pc_.story.database;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import java.util.List;

/**
 * Created by pc- on 2017/7/9.
 */
public class LiteOrmManager {


    public  Context context;
    public LiteOrm liteOrm;

    public static  LiteOrmManager liteOrmManager;
    public static  LiteOrmManager newInstance(Context context){
        if(liteOrmManager==null){
            synchronized (LiteOrmManager.class){
                if(liteOrmManager==null){
                    liteOrmManager=new LiteOrmManager(context);
                }

            }
        }
        return liteOrmManager;
    }
    public LiteOrmManager(Context context){
        DataBaseConfig config = new DataBaseConfig(context.getApplicationContext(), "liteOrm.db");
        config.dbVersion = 1;
        config.onUpdateListener = null;
        liteOrm = LiteOrm.newSingleInstance(config);
    }


    public void save(OrderItem orderItem){
        liteOrm.save(orderItem);
    }
    public void update(OrderItem orderItem){
       liteOrm.update(orderItem);
   }
    public <T> List<T> getQueryAll(Class<T> cla) {
        return liteOrm.query(cla);
    }

    public void save(Object object,int i){
        if(i==1){
            liteOrm.save((OrderItem) object);
        }else if(i==2){
            liteOrm.save((GoodsJudgeItem) object);
        }else if(i==3){
            liteOrm.save((SendJudgeItem)  object);
        }else if(i==4){
            liteOrm.save((AddressItem)  object);

        }
    }

    public void update(Object object,int i){
        if(i==1){
            liteOrm.update((OrderItem) object);
        }else if(i==2){
            liteOrm.update((GoodsJudgeItem) object);
        }else if(i==3){
            liteOrm.update((SendJudgeItem) object);
        }else if(i==4){
            liteOrm.update((AddressItem) object);

        }
    }
    public void delete(Object object,int i){
        if(i==1){
            liteOrm.delete((AddressItem) object);
        }else if(i==2){
            liteOrm.delete(object.getClass());
        }
    }

}
