package com.bwie.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.presenter.PayPresenter;
import com.bwie.mall.view.PayView;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 21:06:18
 * @Description:
 */
public class PayActivity extends BaseActivity<PayPresenter> implements PayView {
    @BindView(R.id.ck_money)
    CheckBox ckMoney;
    @BindView(R.id.ck_weixin)
    CheckBox ckWeixin;
    @BindView(R.id.ck_zhifubao)
    CheckBox ckZhifubao;
    @BindView(R.id.pay_button)
    Button payButton;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private String orderId;
    private String sumPrice;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        sumPrice = intent.getStringExtra("sumPrice");
        payButton.setText("支付" + sumPrice + "0元");
    }

    @Override
    public PayPresenter getPresenter() {
        presenter = new PayPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        /**
         * 点击支付按钮
         */
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //选中余额支付状态
                if (ckMoney.isChecked()) {
                    presenter.onRelated(userId, sessionId, orderId, 1);
                    return;
                }//选中余额支付状态
                else if (ckWeixin.isChecked()) {
                    presenter.onRelated(userId, sessionId, orderId, 2);
                    return;
                }//选中余额支付状态
                else if (ckZhifubao.isChecked()) {
                    presenter.onRelated(userId, sessionId, orderId, 3);
                    return;
                } else {
                    Toast.makeText(PayActivity.this, "请选择支付方式", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getPayViewData(SyncShopCarBean syncShopCarBean) {
        if (syncShopCarBean != null) {
            String message = syncShopCarBean.getMessage();
            String status = syncShopCarBean.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(PayActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PayActivity.this, ShowActivity.class);
                intent.putExtra("codePay","1");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(PayActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
