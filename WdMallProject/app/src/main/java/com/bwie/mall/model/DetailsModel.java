package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 13:39:03
 * @Description:
 */
public class DetailsModel {

    public void getHttpData(String commodityId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getDetails(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShopDetails>() {
                    @Override
                    public void onNext(ShopDetails detailsBean) {
                      //  Log.i("xxx", "onNext: "+detailsBean.getMessage());
                        if (detailsListener!=null){
                            ShopDetails.ResultBean result = detailsBean.getResult();
                            detailsListener.onResult(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                      //  Log.i("xxx", "onNext: "+t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface onDetailsListener{
        void onResult(ShopDetails.ResultBean result);
    }
    public onDetailsListener detailsListener;

    public void setDetailsListener(onDetailsListener detailsListener) {
        this.detailsListener = detailsListener;
    }
}
