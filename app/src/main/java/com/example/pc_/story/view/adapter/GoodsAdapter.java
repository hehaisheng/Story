package com.example.pc_.story.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pc_.story.R;
import com.example.pc_.story.model.MeatModel;

import java.util.List;

/**
 * Created by pc- on 2017/8/13.
 */
public class GoodsAdapter extends BaseQuickAdapter<MeatModel> {

    public IAddGoodsPay iAddGoodsPay;

    public GoodsAdapter(int layoutResId, List<MeatModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MeatModel meatModel) {


            baseViewHolder.setText(R.id.item_goods_name,meatModel.getMeatType()).setText(R.id.goods_danJia,meatModel.getMeatMoney()+"")
                    .setText(R.id.item_main_shop_type,meatModel.getShopName());

        baseViewHolder.getView(R.id.goods_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iAddGoodsPay.addPayMoney(meatModel.getMeatMoney());
                int shopNameLength=meatModel.getShopName().length();
                int shopNameBoth=9-shopNameLength;
                String space="";
                for(int i=0;i<shopNameBoth;i++){
                    space+="....";
                }
                iAddGoodsPay.addGoodsName("店名:"+meatModel.getShopName()+space+meatModel.getMeatType()+"半斤"+"@");
            }
        });
    }
    public void setIAddGoodsPay(IAddGoodsPay iAddGoodsPay){
        this.iAddGoodsPay=iAddGoodsPay;
    }
    public interface  IAddGoodsPay{
        void  addPayMoney(int money);
        void  addGoodsName(String goodsName);
    }
}
