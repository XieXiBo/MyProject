package com.bwie.mall.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mall.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/12 19:50:51
 * @Description:Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public T presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取fragment的View
        View view = inflater.inflate(getFragmentLayout(), null, false);
        unbinder = ButterKnife.bind(this, view);
        //初始化页面
        initView();
        presenter = getPresenter();
        presenter.attachView(presenter);
        initData();
        return view;
    }

    public abstract int getFragmentLayout();

    public abstract void initView();

    protected abstract T getPresenter();

    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.deatchView();
        unbinder.unbind();
    }
}
