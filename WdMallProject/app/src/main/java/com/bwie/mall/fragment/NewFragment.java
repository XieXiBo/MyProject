package com.bwie.mall.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.bwie.mall.R;
import com.bwie.mall.adapter.SelectBillAdapter;
import com.bwie.mall.bean.SelectBillBean;
import com.bwie.mall.presenter.MenuPresenter;
import com.bwie.mall.view.MenuView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 19:57:49
 * @Description:
 */
public class NewFragment extends BaseFragment<MenuPresenter> implements MenuView {
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    private int index;
    private SharedPreferences sp;
    private Map<String, String> map;


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {
        rlvNew.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected MenuPresenter getPresenter() {
        presenter = new MenuPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {

        //默认加载全部订单
        //0=查看全部  1=查看待付款  2=查看待收货  3=查看待评价  9=查看已完成
        sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String userId = sp.getString("userId", "");
        String sessionId = sp.getString("sessionId", "");
        if (!TextUtils.isEmpty(userId + "") && !TextUtils.isEmpty(sessionId)) {
            map = new HashMap<>();
            map.put("userId", userId);
            map.put("sessionId", sessionId);
        }
        Bundle bundle = getArguments();
        index = bundle.getInt("index");

        switch (index) {
            case 0:
                presenter.selectBill(map, 0);
                break;
            case 1:
                presenter.selectBill(map, 1);
                break;
            case 2:
                presenter.selectBill(map, 2);
                break;
            case 3:
                presenter.selectBill(map, 3);
                break;
            case 4:
                presenter.selectBill(map, 9);
                break;
        }

    }

    /**
     * 静态工厂
     * 调用传进来下标
     */
    public static NewFragment newInstance(int index) {
        NewFragment newFragment = new NewFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void getMenuStateViewData(SelectBillBean selectBillBean) {
        if (selectBillBean!=null){
            List<SelectBillBean.OrderListBean> orderList = selectBillBean.getOrderList();
            SelectBillAdapter selectBillAdapter = new SelectBillAdapter(orderList, getActivity());
            rlvNew.setAdapter(selectBillAdapter);
        }

    }
}
