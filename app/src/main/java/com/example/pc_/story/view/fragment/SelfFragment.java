package com.example.pc_.story.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc_.story.R;
import com.example.pc_.story.view.activity.AddressActivity;
import com.example.pc_.story.view.activity.GoodsJudgeActivity;
import com.example.pc_.story.view.activity.SendJudgeActivity;
import com.example.pc_.story.view.custom.CustomImageView;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/13.
 */
public class SelfFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.self_image)
    CustomImageView selfImage;
    @BindView(R.id.self_address_name)
    ImageView selfAddressName;
    @BindView(R.id.self_pay_name)
    ImageView selfPayName;
    @BindView(R.id.self_quit_text)
    TextView selfQuitText;
    @BindView(R.id.self_judge_text)
    TextView selfJudgeText;
    @BindView(R.id.self_send_text)
    TextView selfSendText;

    public  Intent intent;

    @Override
    public void rxPreLoad() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.self_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

        selfJudgeText.setOnClickListener(this);
        selfSendText.setOnClickListener(this);
        selfAddressName.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.self_judge_text:
                startToActivity(1);
                break;
            case R.id.self_send_text:
                startToActivity(2);
                break;
            case R.id.self_address_name:
                startToActivity(3);
                break;
        }

    }

    public void startToActivity(int i){

        switch (i){
            case 1:
                intent=new Intent(getActivity(), GoodsJudgeActivity.class);
                break;
            case 2:
                intent=new Intent(getActivity(), SendJudgeActivity.class);
                break;
            case 3:
                intent=new Intent(getActivity(), AddressActivity.class);
                break;
        }
        startActivity(intent);
    }
}
