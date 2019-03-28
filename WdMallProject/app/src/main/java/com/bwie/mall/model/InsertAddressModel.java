package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.InsertAddressBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 18:24:07
 * @Description:
 */
public class InsertAddressModel {
    public void insertAddress(String userId, String sessionId, Map<String, String> params) {
      //  Log.i("xxx", "onNext: "+userId+sessionId+params.toString());
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.insertAddress(userId, sessionId,params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<InsertAddressBean>() {
                    @Override
                    public void onNext(InsertAddressBean insertAddressBean) {
                        Log.i("xxx", "onNext: "+insertAddressBean.getMessage());
                        if (insertAddress!=null){
                            insertAddress.onInsert(insertAddressBean);
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

    public interface onInsertAddress{
        void onInsert(InsertAddressBean insertAddressBean);
    }
    public onInsertAddress insertAddress;

    public void setInsertAddress(onInsertAddress insertAddress) {
        this.insertAddress = insertAddress;
    }
}
