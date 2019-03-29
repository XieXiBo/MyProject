package com.bwie.mall.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.activity.SortActivity;
import com.bwie.mall.adapter.HomeAdapter;
import com.bwie.mall.adapter.SearchAdapter;
import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.presenter.HomePresenter;
import com.bwie.mall.view.HomeView;
import com.bwie.mall.wiget.CustomSearch;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:主页 RecycleView条目嵌套
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    @BindView(R.id.search_home)
    CustomSearch search_Home;
    @BindView(R.id.rlv_home)
    RecyclerView rlv_Home;
    private HomeAdapter homeAdapter;
    private GoodsBean.ResultBean goods;
    private BannerBean banner;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        rlv_Home.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected HomePresenter getPresenter() {
        presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        presenter.onRelated();

        //点击搜索框跳转
        search_Home.setOnClickSearchEdit(new CustomSearch.onClickSearchEdit() {
            @Override
            public void onResult() {
                startActivity(new Intent(getActivity(), SortActivity.class));
            }
        });

    }

    @Override
    public void getBannerData(BannerBean bannerBean) {
        //banner回调数据
        banner = bannerBean;
        if (banner != null) {
            homeAdapter = new HomeAdapter(getActivity(), goods, banner);
            rlv_Home.setAdapter(homeAdapter);
        }
    }

    @Override
    public void getShowData(GoodsBean.ResultBean result) {
        //主页显示数据
        goods = result;
        if (goods != null) {
            homeAdapter = new HomeAdapter(getActivity(), goods, banner);
            rlv_Home.setAdapter(homeAdapter);
        }
    }

}
