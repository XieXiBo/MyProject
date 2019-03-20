package com.bwie.mall.presenter;

import com.bwie.mall.bean.LoginBean;
import com.bwie.mall.model.LoginModel;
import com.bwie.mall.view.LoginView;

import java.util.Map;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:24:46
 * @Description:
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private final LoginView loginView;
    private final LoginModel loginModel;

    public LoginPresenter(LoginView view) {
        loginView = view;
        loginModel = new LoginModel();
    }

    public void sendParams(Map<String, String> params) {
        loginModel.login(params);
        //回调数据
        loginModel.setLoginListener(new LoginModel.onLoginListener() {
            @Override
            public void onResult(LoginBean loginBean) {
                loginView.getViewData(loginBean);
            }
        });
    }
}
