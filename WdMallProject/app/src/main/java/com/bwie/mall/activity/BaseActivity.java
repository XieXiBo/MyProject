package com.bwie.mall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwie.mall.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/12 19:48:00
 * @Description:Activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取Ativity的View
        setContentView(getActivityLayout());
        ButterKnife.bind(this);
        //初始化页面
        initView();
        //获取p层方法
        presenter = getPresenter();
        //对象添加给弱引用，为解决内存泄漏
        presenter.attachView(presenter);
        initData();

    }

    public abstract int getActivityLayout();

    public abstract void initView();

    public abstract T getPresenter();

    public abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空弱引用对象
        presenter.deatchView();
    }
}
