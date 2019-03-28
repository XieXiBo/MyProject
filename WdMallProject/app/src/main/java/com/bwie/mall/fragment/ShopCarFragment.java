package com.bwie.mall.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.adapter.ShopCarAdapter;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.presenter.ShopCatPresenter;
import com.bwie.mall.view.ShopCarView;

import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:
 */
public class ShopCarFragment extends BaseFragment<ShopCatPresenter> implements ShopCarView {
    @BindView(R.id.rlv_shop)
    RecyclerView rlv_Shop;
    @BindView(R.id.shop_box_all)
    CheckBox shopBoxAll;
    @BindView(R.id.shop_text_allprice)
    TextView shopTextAllprice;
    @BindView(R.id.shop_text_go)
    TextView shopTextGo;
    private SharedPreferences sp_login;
    private String userId;
    private String sessionId;
    private ShopCarAdapter adapter;
    private List<QueryCartBean.ResultBean> result;
    private boolean flag = false;

    @Override
    public void onResume() {
        super.onResume();
        sp_login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            presenter.onRelated(userId, sessionId);
        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_shopcar;
    }

    @Override
    public void initView() {

        rlv_Shop.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv_Shop.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));
    }

    @Override
    protected ShopCatPresenter getPresenter() {
        presenter = new ShopCatPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            presenter.onRelated(userId, sessionId);
        }
        /**
         * 全选点击事件
         */
        shopBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll(shopBoxAll.isChecked());
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void getShopCarData(QueryCartBean queryCartBean) {

        if (queryCartBean != null) {
            result = queryCartBean.getResult();
            if (result.size() != 0) {
                adapter = new ShopCarAdapter(getActivity(), result, flag);
                rlv_Shop.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                /**
                 * 商品数量改变,条目复选框点击 --> 回调监听
                 */
                adapter.setCallbackListener(new ShopCarAdapter.onCallbackListener() {
                    @Override
                    public void callback(List<QueryCartBean.ResultBean> list) {
                        double priceAll = 0;
                        int num = 0;
                        int totalNum = 0;
                        //遍历集合根据状态改变总价格总数量
                        for (int i = 0; i < list.size(); i++) {
                            boolean ischeck = list.get(i).isIscheck();
                           // Log.i("xxx", "callback: " + ischeck);
                            //存入所有商品数量
                            totalNum += list.get(i).getCount();
                            //选中状态
                            if (ischeck) {
                                //存入当前商品（价钱*数量）
                                priceAll += (list.get(i).getPrice() * list.get(i).getCount());
                                //存入当前商品数量
                                num += list.get(i).getCount();
                            }
                        }
                        if (num == totalNum) {
                            shopBoxAll.setChecked(true);
                        } else {
                            shopBoxAll.setChecked(false);
                        }
                        shopTextAllprice.setText("" + priceAll);
                        shopTextGo.setText("去结算(" + num + ")");
                    }
                });
            }
        }

    }

    /**
     * 全选方法
     *
     * @param checked
     */
    private void checkAll(boolean checked) {

        double priceAll = 0.0;
        int num = 0;
        for (int i = 0; i < result.size(); i++) {
            //修改商品的复选框
            result.get(i).setIscheck(checked);
            priceAll = priceAll + (result.get(i).getPrice() * result.get(i).getCount());
            num = num + result.get(i).getCount();
        }
        if (checked) {
            shopTextAllprice.setText("合计：" + priceAll);
            shopTextGo.setText("去结算(" + num + ")");
        } else {
            shopTextAllprice.setText("合计：" + 0.0);
            shopTextGo.setText("去结算(" + 0 + ")");
        }
    }

}
