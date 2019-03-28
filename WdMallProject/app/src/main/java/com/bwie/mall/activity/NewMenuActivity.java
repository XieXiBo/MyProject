package com.bwie.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.MineAddressAdapter;
import com.bwie.mall.adapter.ShopCarAdapter;
import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.NewMenuBean;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.SubmitMenuBean;
import com.bwie.mall.presenter.NewMenuPresenter;
import com.bwie.mall.view.NewMenuView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 10:21:57
 * @Description:
 */
public class NewMenuActivity extends BaseActivity<NewMenuPresenter> implements NewMenuView {
    @BindView(R.id.rlx1_newmune)
    RecyclerView rlx1Newmune;
    @BindView(R.id.rlx2_newmune)
    RecyclerView rlx2Newmune;
    @BindView(R.id.num_newmenu)
    TextView numNewmenu;
    @BindView(R.id.price_newmenu)
    TextView priceNewmenu;
    @BindView(R.id.checkMatch_address)
    Button checkMatchAddress;
    @BindView(R.id.submit_menu)
    TextView submitMenu;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private List<QueryCartBean.ResultBean> resultBeans = new ArrayList<>();
    private boolean flag = true;
    private double sumPrice;
    private int sumNum;
    private int addressId;
    private MineAddressAdapter addressAdapter;
    private List<MineAddressBean.ResultBean> list;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_newmenu;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);

        //选择更多地址按钮
        checkMatchAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewMenuActivity.this, MineAddressActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //提交订单按钮
        submitMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SubmitMenuBean> list = new ArrayList<>();
                for (int i = 0; i < resultBeans.size(); i++) {
                    list.add(new SubmitMenuBean(resultBeans.get(i).getCount(), resultBeans.get(i).getCommodityId()));
                }
                String data = new Gson().toJson(list);

                presenter.submitMenu(userId, sessionId, data, sumPrice, addressId);
                //  Log.i("xxx", "onClick: "+userId+"  "+ sessionId+"  "+data+"  "+sumPrice+"  "+addressId);

            }
        });
    }

    @Override
    public NewMenuPresenter getPresenter() {
        presenter = new NewMenuPresenter(this);
        return presenter;
    }


    //接受传过来的商品信息
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEventBus(ShopDetails.ResultBean buyBean) {

        String[] split = buyBean.getPicture().split(",");
        resultBeans.add(new QueryCartBean.ResultBean(buyBean.getCommodityId(), buyBean.getCommodityName(), 1, split[0], buyBean.getPrice(), true));

    }


    @Override
    public void initData() {
        //默认请求地址
        presenter.onRelated(userId, sessionId);
    }

    @Override
    public void getNewMenuViewData(MineAddressBean mineAddressBean) {
        if (mineAddressBean != null) {
            List<MineAddressBean.ResultBean> result = mineAddressBean.getResult();
            //第一次进入
            //展示默认收货地址
            list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getWhetherDefault() == 1) {
                    list.add(result.get(i));
                    //获取默认收货地址Id
                    addressId = list.get(i).getId();
                }
            }
            addressAdapter = new MineAddressAdapter(NewMenuActivity.this, list);
            rlx1Newmune.setAdapter(addressAdapter);
        }
        if (resultBeans.size() != 0) {
            // Log.i("xxx", "getNewMenuViewData: "+resultBeans.toString());
            ShopCarAdapter adapter = new ShopCarAdapter(NewMenuActivity.this, resultBeans, flag);
            rlx2Newmune.setAdapter(adapter);

            //默认显示需要支付信息
            sumPrice = 0;
            sumNum = 0;
            for (int i = 0; i < resultBeans.size(); i++) {
                sumPrice += (resultBeans.get(i).getPrice() * resultBeans.get(i).getCount());
                sumNum += resultBeans.get(i).getCount();
            }
            priceNewmenu.setText(sumPrice + "");
            numNewmenu.setText(sumNum + "");

            //当数量改变后的监听
            adapter.setCallbackListener(new ShopCarAdapter.onCallbackListener() {
                @Override
                public void callback(List<QueryCartBean.ResultBean> list) {
                    sumPrice = 0;
                    sumNum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isIscheck()) {
                            sumPrice += (list.get(i).getPrice() * list.get(i).getCount());
                            sumNum += list.get(i).getCount();
                        }
                    }
                    priceNewmenu.setText(sumPrice + "0");
                    numNewmenu.setText(sumNum + "");
                }
            });

        }
    }

    @Override
    public void getCreateMenuData(NewMenuBean newMenuBean) {
        if (newMenuBean != null) {
            String message = newMenuBean.getMessage();
            String status = newMenuBean.getStatus();
            String orderId = newMenuBean.getOrderId();
            /**
             * 判断是否创建订单成功
             */
            if (status.equals("0000")) {
                Toast.makeText(NewMenuActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewMenuActivity.this, PayActivity.class);
                intent.putExtra("orderId", orderId+"");
                intent.putExtra("sumPrice", sumPrice+"");
                startActivity(intent);
            } else {
                Toast.makeText(NewMenuActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && data != null) {
            list.clear();
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String address = data.getStringExtra("address");
            String mr = data.getStringExtra("mr");
            String id = data.getStringExtra("id");
            addressId = Integer.parseInt(id);
            list.add(new MineAddressBean.ResultBean(address, phone, name, Integer.parseInt(mr)));
            addressAdapter = new MineAddressAdapter(NewMenuActivity.this, list);
            rlx1Newmune.setAdapter(addressAdapter);
        }
    }
}
