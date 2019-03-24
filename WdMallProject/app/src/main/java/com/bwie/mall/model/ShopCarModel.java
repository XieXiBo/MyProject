package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/22 19:32:06
 * @Description:
 */
public class ShopCarModel {
    public void getQueryCart(String sessionId, String userId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getQueryCart(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryCartBean>() {
                    @Override
                    public void onNext(QueryCartBean queryCartBean) {
                        // List<QueryCartBean.ResultBean> result = queryCartBean.getResult();
                        // Log.i("xxx", "onNext: "+queryCartBean.getMessage());
                        if (queryCarListener != null) {
                            queryCarListener.onResult(queryCartBean);
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
    public interface onQueryCarListener {
        void onResult(QueryCartBean queryCartBean);
    }

    public onQueryCarListener queryCarListener;

    public void setQueryCarListener(onQueryCarListener queryCarListener) {
        this.queryCarListener = queryCarListener;
    }
}
