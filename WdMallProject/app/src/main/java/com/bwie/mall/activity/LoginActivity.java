package com.bwie.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.bean.LoginBean;
import com.bwie.mall.presenter.LoginPresenter;
import com.bwie.mall.utils.TelUtils;
import com.bwie.mall.view.LoginView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/13 19:45:25
 * @Description:
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.edit_phone_login)
    EditText ed_phone;
    @BindView(R.id.edit_pwd_login)
    EditText ed_pwd;
    @BindView(R.id.ck_rem_pwd)
    CheckBox rem_pwd;
    @BindView(R.id.regist_login)
    TextView regist;
    @BindView(R.id.login_login)
    Button login;
    private SharedPreferences sp;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        //创建sp
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        ButterKnife.bind(this);
        //sp判断
        if (sp.getBoolean("rem_pwd", false)) {
            String sp_phone = sp.getString("phone", "").toString();
            String sp_pwd = sp.getString("pwd", "").toString();
            rem_pwd.setChecked(true);
            ed_phone.setText(sp_phone);
            ed_pwd.setText(sp_pwd);
        }
    }

    @Override
    public LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {

        //立即注册按钮点击
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                finish();
            }
        });

        //点击登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String phone = ed_phone.getText().toString();
                String pwd = ed_pwd.getText().toString();
                boolean mobileNO = TelUtils.isMobileNO(phone);
                //非空判断格式判断
                if (phone == null) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT);
                    return;
                } else if (pwd == null) {
                    Toast.makeText(LoginActivity.this, "请输入登录密码", Toast.LENGTH_SHORT);
                    return;
                } else {
                    if (!mobileNO) {
                        Toast.makeText(LoginActivity.this, "手机号格式不对", Toast.LENGTH_SHORT);
                        return;
                    } else if (pwd.length() < 3) {
                        Toast.makeText(LoginActivity.this, "密码格式不对", Toast.LENGTH_SHORT);
                        return;
                    } else {
                        /**
                         * 记住密码操作
                         */
                        SharedPreferences.Editor edit = sp.edit();
                        if (rem_pwd.isChecked()) {
                            edit.putBoolean("rem_pwd", true);
                            edit.putString("phone", phone);
                            edit.putString("pwd", pwd);
                        } else {
                            edit.clear();
                        }
                        edit.commit();
                    }
                }
                //map存值。传给p逻辑处理
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);
                presenter.sendParams(params);
            }
        });
    }

    @Override
    public void getViewData(LoginBean loginBean) {
        // Log.i("xxx", "getViewData: "+loginBean);
        if (loginBean != null) {
            String status = loginBean.getStatus();
            String message = loginBean.getMessage();
            if (status.equals("0000")){
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                LoginBean.ResultBean result = loginBean.getResult();
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("headPic", result.getHeadPic());
                edit.putString("nickName", result.getNickName());
                edit.putString("sessionId", result.getSessionId());
                edit.putString("userId", String.valueOf(result.getUserId()));
                edit.commit();
            }else{
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        }

    }

}
