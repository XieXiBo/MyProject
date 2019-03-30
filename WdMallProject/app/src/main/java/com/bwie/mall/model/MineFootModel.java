package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.MineFootBean;
import com.bwie.mall.bean.MineWalletBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 15:29:46
 * @Description:
 */
public class MineFootModel {
    public void getFoot(String userId, String sessionId, int page) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getFoot(userId,sessionId,page,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MineFootBean>() {
                    @Override
                    public void onNext(MineFootBean mineFootBean) {
                        //Log.i("xxx", "onNext: "+mineWalletBean.getMessage());
                        if (footListener!=null){
                            footListener.onResult(mineFootBean);
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
    public interface onFootListener{
        void onResult(MineFootBean mineFootBean);
    }

    public onFootListener footListener;

    public void setFootListener(onFootListener footListener) {
        this.footListener = footListener;
    }
}
