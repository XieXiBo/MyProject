package com.bwie.mall.fragment;

import android.os.Bundle;

import com.bwie.mall.R;
import com.bwie.mall.presenter.MenuPresenter;
import com.bwie.mall.view.MenuView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 19:57:49
 * @Description:
 */
public class NewFragment extends BaseFragment<MenuPresenter> implements MenuView {
    private int index;

    @Override
    public int getFragmentLayout() {
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        switch (index) {
            case 0:
                return R.layout.fragment_allmenu;
            case 1:
                return R.layout.fragment_waitprice;
            case 2:
                return R.layout.fragment_waitgood;
            case 3:
                return R.layout.fragment_waitpj;
            default:
                return R.layout.fragment_over;
        }

    }

    @Override
    public void initView() {

    }

    @Override
    protected MenuPresenter getPresenter() {
        presenter = new MenuPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {

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
}
