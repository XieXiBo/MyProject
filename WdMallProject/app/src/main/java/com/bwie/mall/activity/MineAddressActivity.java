package com.bwie.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.MineAddressAdapter;
import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.MrAddressBean;
import com.bwie.mall.presenter.MineAddressPresenter;
import com.bwie.mall.view.MineAddressView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineAddressActivity extends BaseActivity<MineAddressPresenter> implements MineAddressView {

    @BindView(R.id.rlv_address)
    RecyclerView rlvAddress;
    @BindView(R.id.insert_address)
    Button insertAddress;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onRelated(userId, sessionId);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        //设置RecycleView
        rlvAddress.setLayoutManager(new LinearLayoutManager(MineAddressActivity.this));
    }

    @Override
    public MineAddressPresenter getPresenter() {
        presenter = new MineAddressPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        presenter.onRelated(userId, sessionId);
    }

    @OnClick(R.id.insert_address)
    public void onViewClicked() {
        //点击新增
        startActivity(new Intent(MineAddressActivity.this, InsterAddressActivity.class));
    }

    @Override
    public void getMineAddressView(MineAddressBean mineAddressBean) {
        if (mineAddressBean != null) {
            final List<MineAddressBean.ResultBean> result = mineAddressBean.getResult();
            MineAddressAdapter adapter = new MineAddressAdapter(MineAddressActivity.this, result);
            rlvAddress.setAdapter(adapter);
            /**
             * 设置默认收货地址
             */
            adapter.setMrAddress(new MineAddressAdapter.onMrAddress() {
                @Override
                public void onResult(boolean isChecked, int i) {
                    if (isChecked) {
                        int id = result.get(i).getId();
                        presenter.serMrAddress(userId, sessionId, id);
                    }
                }
            });
            /**
             * 回传给订单页面的选择其他地址
             */
            adapter.setIntentData(new MineAddressAdapter.onIntentData() {
                @Override
                public void onResult(int i) {
                    Intent intent = new Intent();
                    intent.putExtra("name", result.get(i).getRealName());
                    intent.putExtra("phone", result.get(i).getPhone());
                    intent.putExtra("address", result.get(i).getAddress());
                    intent.putExtra("mr",result.get(i).getWhetherDefault()+"");
                    intent.putExtra("id",result.get(i).getId()+"");
                    setResult(1, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void getMrAddressView(MrAddressBean mrAddressBean) {
        if (mrAddressBean != null) {
            String message = mrAddressBean.getMessage();
            String status = mrAddressBean.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(MineAddressActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(MineAddressActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
