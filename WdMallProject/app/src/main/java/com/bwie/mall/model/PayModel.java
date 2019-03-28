package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 21:08:32
 * @Description:
 */
public class PayModel {
    public void goPay(String userId, String sessionId, String orderId, int i) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.goPay(Integer.parseInt(userId),sessionId,orderId,i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SyncShopCarBean>() {
                    @Override
                    public void onNext(SyncShopCarBean syncShopCarBean) {
                      //  Log.i("xxx", "onNext: "+syncShopCarBean.getMessage());
                        if (payListener!=null){
                            payListener.onResult(syncShopCarBean);
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
    public interface onPayListener{
        void onResult(SyncShopCarBean syncShopCarBean);
    }
    public onPayListener payListener;

    public void setPayListener(onPayListener payListener) {
        this.payListener = payListener;
    }
}
