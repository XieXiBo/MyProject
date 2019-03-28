package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.MrAddressBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 17:48:08
 * @Description:
 */
public class MineAddressModel {
    public void getAddress(String userId, String sessionId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getAddress(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MineAddressBean>() {
                    @Override
                    public void onNext(MineAddressBean mineAddressBean) {
                       // Log.i("xxx", "onNext: "+mineAddressBean.getMessage());
                        if (addressListener!=null){
                            addressListener.onAddress(mineAddressBean);
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

    public void getMrAddress(String userId, String sessionId, int id) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.setMrAddress(userId,sessionId,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MrAddressBean>() {
                    @Override
                    public void onNext(MrAddressBean mrAddressBean) {
                        if (mrAddressBean!=null){
                            if (mrAddressBack!=null){
                                mrAddressBack.onResult(mrAddressBean);
                            }
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

    public interface onAddressListener{
        void onAddress(MineAddressBean mineAddressBean);
    }
    public onAddressListener addressListener;

    public void setAddressListener(onAddressListener addressListener) {
        this.addressListener = addressListener;
    }

    public interface onMrAddressBack{
        void onResult(MrAddressBean mrAddressBean);
    }

    public onMrAddressBack mrAddressBack;

    public void setMrAddressBack(onMrAddressBack mrAddressBack) {
        this.mrAddressBack = mrAddressBack;
    }
}
