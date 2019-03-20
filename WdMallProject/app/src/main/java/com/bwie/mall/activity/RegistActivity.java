package com.bwie.mall.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.bean.RegistBean;
import com.bwie.mall.presenter.RegistPresenter;
import com.bwie.mall.utils.TelUtils;
import com.bwie.mall.view.RegistView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/13 19:46:09
 * @Description:
 */
public class RegistActivity extends BaseActivity<RegistPresenter> implements RegistView {


    @BindView(R.id.edit_phone_regist)
    EditText ed_phone;
    @BindView(R.id.edit_yzm_regist)
    EditText ed_yzm;
    @BindView(R.id.edit_pwd_regist)
    EditText ed_pwd;
    @BindView(R.id.regist_regist)
    Button regist;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView() {


    }

    @Override
    public RegistPresenter getPresenter() {
        //实例p
        presenter = new RegistPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        //点击注册按钮
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("aaa", "onClick: ");
                //获取输入框的值
                String phone = ed_phone.getText().toString();
                String pwd = ed_pwd.getText().toString();
                boolean mobileNO = TelUtils.isMobileNO(phone);
                //非空判断格式判断
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegistActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!mobileNO) {
                        Toast.makeText(RegistActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                //非空判断格式判断
                if (pwd == null) {
                    Toast.makeText(RegistActivity.this, "请输入登录密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (pwd.length() < 3) {
                        Toast.makeText(RegistActivity.this, "密码格式不对", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                //map存值。传给p逻辑处理
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);

                //p关联m
                presenter.sendParams(params);
            }
        });
    }


    @Override
    public void getViewData(RegistBean registBean) {
        if (registBean != null) {
            String message = registBean.getMessage();
            String status = registBean.getStatus();
            /**
             * 0000注册成功
             * 其他均不成功
             */
            if (status.equals("0000")) {
                Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }


}