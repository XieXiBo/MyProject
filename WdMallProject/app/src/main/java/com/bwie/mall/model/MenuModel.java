package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.SelectBillBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 19:45:46
 * @Description:
 */
public class MenuModel {
    public void getMenuState(Map<String, String> map, int i) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        Flowable<SelectBillBean> menuState = apiService.getMenuState(map, i, 1, 20);
        //Log.i("xxx", "initData: "+i);
        menuState.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SelectBillBean>() {
                    @Override
                    public void onNext(SelectBillBean selectBillBean) {
                        Log.i("xxx", "onNext: " + selectBillBean.getMessage());
                        if (menuStateLinstener != null) {
                            menuStateLinstener.onResult(selectBillBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        // Log.i("xxx", "onError: "+t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onMenuStateLinstener {
        void onResult(SelectBillBean selectBillBean);
    }

    public onMenuStateLinstener menuStateLinstener;

    public void setMenuStateLinstener(onMenuStateLinstener menuStateLinstener) {
        this.menuStateLinstener = menuStateLinstener;
    }
}
