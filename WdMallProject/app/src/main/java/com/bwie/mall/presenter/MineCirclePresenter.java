package com.bwie.mall.presenter;

import android.widget.BaseAdapter;

import com.bwie.mall.activity.MineCircleActivity;
import com.bwie.mall.view.MineCircleView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 11:32:26
 * @Description:
 */
public class MineCirclePresenter extends BasePresenter<MineCircleView> {

    private final MineCircleView mineCircleView;

    public MineCirclePresenter(MineCircleView view) {
        mineCircleView = view;
    }
}
