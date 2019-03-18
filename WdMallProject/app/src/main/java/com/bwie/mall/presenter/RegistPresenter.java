package com.bwie.mall.presenter;

import com.bwie.mall.activity.RegistActivity;
import com.bwie.mall.view.LoginView;
import com.bwie.mall.view.RegistView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:24:46
 * @Description:
 */
public class RegistPresenter extends BasePresenter<RegistView> {

    private final RegistView registView;

    public RegistPresenter(RegistView view) {
        registView = view;
    }

}
