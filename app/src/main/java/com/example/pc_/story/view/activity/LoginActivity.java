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
public class LoginActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.login_accountEdit)
    EditText loginAccountEdit;
    @BindView(R.id.login_passEdit)
    EditText loginPassEdit;
    @BindView(R.id.login_clickText)
    TextView loginClickText;
    @BindView(R.id.register_clickText)
    TextView registerClickText;
    @BindView(R.id.find_pass)
    TextView findPass;


    public SharedPreferences sharedPreferences;
    public String account;
    public String password;
    public boolean hasAccount;

    @Override
    public int getLayout() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {



    }

    @Override
    public void initData() {
        sharedPreferences=getSharedPreferences("Login",0);
        account=sharedPreferences.getString("Account","0");
        password=sharedPreferences.getString("Password","0");
        hasAccount=sharedPreferences.getBoolean("HasAccount",false);

    }

    @Override
    public void initEvent() {

        loginClickText.setOnClickListener(this);
        registerClickText.setOnClickListener(this);
        findPass.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_clickText:
                String accountString1=loginAccountEdit.getText().toString();
                String passwordString1=loginPassEdit.getText().toString();
                if(accountString1.equals(account)&&passwordString1.equals(password)){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("HasLogin",true);
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    if(!hasAccount){
                        Toast.makeText(this,"无该账号，请先注册",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"重新输入账号和密码",Toast.LENGTH_SHORT).show();
                    }

                }

                break;
            case R.id.register_clickText:
                String accountString=loginAccountEdit.getText().toString();
                String passwordString=loginPassEdit.getText().toString();
                if(accountString.length()!=11){
                    Toast.makeText(this,"账号应该为11位数的电话号码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(passwordString.length()<6){
                    Toast.makeText(this,"密码长度应该大于6",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(accountString.equals(account)){
                    Toast.makeText(this,"该号码已注册过,请登录",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Account",accountString);
                    editor.putString("Password",passwordString);
                    editor.putBoolean("HasLogin",true);
                    editor.putBoolean("HasAccount",true);
                    editor.apply();
                    Toast.makeText(this,"注册成功,自动登录",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.find_pass:
                Intent intent=new Intent(LoginActivity.this,FindPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }


}
