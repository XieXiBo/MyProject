package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.LoginBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/18 18:52:37
 * @Description:
 */
public class LoginModel {
    public void login(Map<String, String> params) {

        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginListener != null) {
                            loginListener.onResult(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onLoginListener {
        void onResult(LoginBean loginBean);
    }

    public onLoginListener loginListener;

    public void setLoginListener(onLoginListener loginListener) {
        this.loginListener = loginListener;
    }
}
