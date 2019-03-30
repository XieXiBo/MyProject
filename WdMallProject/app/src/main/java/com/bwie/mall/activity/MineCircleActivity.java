package com.bwie.mall.activity;

import com.bwie.mall.R;
import com.bwie.mall.presenter.MineCirclePresenter;
import com.bwie.mall.view.MineCircleView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 11:31:42
 * @Description:
 */
public class MineCircleActivity extends BaseActivity<MineCirclePresenter> implements MineCircleView {
    @Override
    public int getActivityLayout() {
        return R.layout.activity_minecircle;
    }

    @Override
    public void initView() {

    }

    @Override
    public MineCirclePresenter getPresenter() {
        presenter = new MineCirclePresenter(this);
        return presenter;
    }

    @Override
    public void initData() {

    }
}
