package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.MineInfoBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 08:14:43
 * @Description:
 */
public class MineInfoModel {

    public void getMineInfo(String userId, String sessionId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        final Flowable<MineInfoBean> mineInfo = apiService.getMineInfo(userId, sessionId);
        mineInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MineInfoBean>() {
                    @Override
                    public void onNext(MineInfoBean mineInfoBean) {
                     //   Log.i("xxx", "onNext: "+mineInfoBean.getMessage());
                        if (mineInfoListener!=null){
                            mineInfoListener.onRelated(mineInfoBean);
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

    public interface onMineInfoListener{
        void onRelated(MineInfoBean mineInfoBean);
    }
    public onMineInfoListener mineInfoListener;

    public void setMineInfoListener(onMineInfoListener mineInfoListener) {
        this.mineInfoListener = mineInfoListener;
    }
}
