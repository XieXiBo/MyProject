package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.MineWalletBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 14:36:01
 * @Description:
 */
public class MineWalletModel {
    public void getWalletData(String userId, String sessionId, int page) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getWallet(userId,sessionId,page,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MineWalletBean>() {
                    @Override
                    public void onNext(MineWalletBean mineWalletBean) {
                        //Log.i("xxx", "onNext: "+mineWalletBean.getMessage());
                        if (walletListener!=null){
                            walletListener.onResult(mineWalletBean);
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
    public interface onWalletListener{
        void onResult(MineWalletBean mineWalletBean);
    }
    public onWalletListener walletListener;

    public void setWalletListener(onWalletListener walletListener) {
        this.walletListener = walletListener;
    }
}
