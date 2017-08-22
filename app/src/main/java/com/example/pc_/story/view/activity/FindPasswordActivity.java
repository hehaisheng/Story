package com.example.pc_.story.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc_.story.R;

import butterknife.BindView;

/**
 * Created by pc- on 2017/8/16.
 */
public class FindPasswordActivity extends BaseActivity {

    @BindView(R.id.find_accountEdit)
    EditText findAccountEdit;
    @BindView(R.id.find_linear1)
    TextView findLinear1;
    @BindView(R.id.find_passEdit)
    EditText findPassEdit;
    @BindView(R.id.find_linear2)
    TextView findLinear2;
    @BindView(R.id.find_clickText)
    TextView findClickText;

    public SharedPreferences sharedPreferences;
    public  String editAccountString;
    public  String editPasswordString;
    public  String account;
    public boolean hasAccount;

    @Override
    public int getLayout() {
        return R.layout.find_password_activity;
    }

    @Override
    public void initView() {

        sharedPreferences=getSharedPreferences("Login",0);
        account=sharedPreferences.getString("Account","0");
        hasAccount=sharedPreferences.getBoolean("HasAccount",false);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

        findClickText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasAccount){
                    editAccountString=findAccountEdit.getText().toString();
                    editPasswordString=findPassEdit.getText().toString();
                    if(!editAccountString.equals(account)){
                        Toast.makeText(FindPasswordActivity.this,"账号不正确",Toast.LENGTH_SHORT).show();
                    }else {
                        if(editPasswordString.length()<6){
                            Toast.makeText(FindPasswordActivity.this,"密码长度应该大于6",Toast.LENGTH_SHORT).show();
                        }else {
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("Password",editPasswordString);
                            editor.apply();
                            Intent intent=new Intent(FindPasswordActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }else{
                    Toast.makeText(FindPasswordActivity.this,"无该账号，请注册",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
