package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.RegistBean;
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
 * @Date: 2019/3/18 19:41:45
 * @Description:
 */
public class RegistModel {
    public void regist(Map<String, String> params) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.regist(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegistBean>() {
                    @Override
                    public void onNext(RegistBean registBean) {
                        if (registListener!=null){
                            registListener.onResult(registBean);
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
    /**
     * 是定义接口回调
     */
    public interface onRegistListener{
        void onResult(RegistBean loginBean);
    }
    public onRegistListener registListener;

    public void setRegistListener(onRegistListener registListener) {
        this.registListener = registListener;
    }
}
