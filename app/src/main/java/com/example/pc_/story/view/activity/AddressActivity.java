package com.example.pc_.story.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_.story.R;
import com.example.pc_.story.database.AddressItem;
import com.example.pc_.story.database.LiteOrmManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/16.
 */
public class AddressActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.add_address_title)
    TextView addAddressTitle;
    @BindView(R.id.add_address_default)
    TextView addAddressDefault;
    @BindView(R.id.add_address_one)
    TextView addAddressOne;
    @BindView(R.id.add_address_two)
    TextView addAddressTwo;
    @BindView(R.id.add_address_province)
    EditText addAddressProvince;
    @BindView(R.id.add_address_city)
    EditText addAddressCity;
    @BindView(R.id.add_address_region)
    EditText addAddressRegion;
    @BindView(R.id.add_address_definite)
    EditText addAddressDefinite;
    @BindView(R.id.add_clickText)
    TextView addClickText;
    @BindView(R.id.add_delete_one)
    TextView addDeleteOne;
    @BindView(R.id.add_delete_two)
    TextView addDeleteTwo;
    @BindView(R.id.add_delete_three)
    TextView addDeleteThree;


    public LiteOrmManager liteOrmManager;
    public List<AddressItem> addressItemList = new ArrayList<>();

    public MyDialogFragment myDialogFragment;

    @Override
    public int getLayout() {
        return R.layout.add_address_activity;
    }

    @Override
    public void initView() {

        liteOrmManager = LiteOrmManager.newInstance(this);
        addressItemList = liteOrmManager.getQueryAll(AddressItem.class);
        if (addressItemList.size() == 0) {
            for (int i = 0; i < 3; i++) {
                AddressItem addressItem = new AddressItem();
                if (i == 0) {
                    addressItem.setDefault(true);
                }
                addressItem.setProvinceString("广东省");
                addressItem.setCityString("广州市");
                addressItem.setRegionString("海珠区");
                addressItem.setDefiniteString("晓园北5号楼904");
                liteOrmManager.save(addressItem, 4);
                addressItemList.add(addressItem);
            }
        }
        forGetData(addressItemList);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {


        addDeleteOne.setOnClickListener(this);
        addDeleteTwo.setOnClickListener(this);
        addDeleteThree.setOnClickListener(this);

        addAddressTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addClickText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String provinceStr = addAddressProvince.getText().toString();
                String cityStr = addAddressCity.getText().toString();
                String regionStr = addAddressRegion.getText().toString();
                String definiteStr = addAddressDefinite.getText().toString();
                if (provinceStr.length() == 0 || cityStr.length() == 0 || regionStr.length() == 0 || definiteStr.length() == 0) {
                    Toast.makeText(AddressActivity.this, "请认真填写地址", Toast.LENGTH_SHORT).show();
                } else {
                    if(addressItemList.size()==3){
                        Toast.makeText(AddressActivity.this, "请删除一个地址，再添加新地址", Toast.LENGTH_SHORT).show();
                    }else {
                        AddressItem addressItem = new AddressItem();
                        addressItem.setProvinceString(provinceStr);
                        addressItem.setCityString(cityStr);
                        addressItem.setRegionString(regionStr);
                        addressItem.setDefiniteString(definiteStr);
                        liteOrmManager.save(addressItem,4);
                    }
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add_delete_one:
                if(!addAddressDefault.getText().equals("暂无地址信息")){
                     deleteAddressData(0);
                }
                break;
            case R.id.add_delete_two:
                if(!addAddressOne.getText().equals("暂无地址信息")){
                    deleteAddressData(1);
                }
                break;
            case R.id.add_delete_three:
                if(!addAddressTwo.getText().equals("暂无地址信息")){
                    deleteAddressData(2);
                }
                break;
        }
    }

    public void deleteAddressData(final int j){
        //能进入该方法，说明j位置由数据
        Bundle bundle = new Bundle();
        bundle.putString("dialogContent", "是否确定删除该地址信息");
        myDialogFragment = MyDialogFragment.newInstance(bundle);
        myDialogFragment.show(getFragmentManager(),"dialogFragment");
        myDialogFragment.setDialogListener(new MyDialogFragment.IDialogListener() {
            @Override
            public void confirm() {
                AddressItem addressItem = addressItemList.get(j);
                List<AddressItem> addressItemsTemp = new ArrayList<>();
                for (int i = 0; i < addressItemList.size(); i++) {
                    if(i!=j){
                        addressItemsTemp.add(addressItemList.get(i));
                    }
                }
                liteOrmManager.delete(addressItem, 2);
                for (int i = 0; i < addressItemsTemp.size(); i++) {
                    liteOrmManager.save(addressItemsTemp.get(i), 4);
                }
                forGetData(addressItemsTemp);
                myDialogFragment.dismiss();
            }
            @Override
            public void cancel() {

                myDialogFragment.dismiss();

            }
        });
    }

    public  void forGetData(List<AddressItem> addressItems1){
        for (int i = 0; i <addressItems1.size(); i++) {
            String province = addressItems1.get(i).getProvinceString();
            String city = addressItems1.get(i).getCityString();
            String region = addressItems1.get(i).getRegionString();
            String definite = addressItems1.get(i).getDefiniteString();
            if (i==0) {
                addAddressDefault.setText(new StringBuilder().append(province).append(city).append(region).append(definite).toString());
            }else if (i == 1) {
                    addAddressOne.setText(new StringBuilder().append(province).append(city).append(region).append(definite).toString());
                } else if (i == 2) {
                    addAddressTwo.setText(new StringBuilder().append(province).append(city).append(region).append(definite).toString());
                }

            }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myDialogFragment!=null){
            myDialogFragment.setDialogListener(null);
            myDialogFragment=null;
        }
    }
}
