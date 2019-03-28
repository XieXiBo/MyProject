package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 13:39:03
 * @Description:
 */
public class DetailsModel {
    //详情展示
    public void getHttpData(String commodityId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getDetails(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShopDetails>() {
                    @Override
                    public void onNext(ShopDetails detailsBean) {
                        //  Log.i("xxx", "onNext: "+detailsBean.getMessage());
                        if (detailsListener != null) {
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

    //出现购物车
    public void getQueryCart(String sessionId, String userId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getQueryCart(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryCartBean>() {
                    @Override
                    public void onNext(QueryCartBean queryCartBean) {
                        // List<QueryCartBean.ResultBean> result = queryCartBean.getResult();
                        //  Log.i("xxx", "onNext: "+queryCartBean.getMessage());
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

    /**
     * (同步)添加购物车网络请求
     *
     * @param userId
     * @param sessionId
     * @param data
     */
    public void getSyncShopCart(String userId, String sessionId, String data) {
        // Log.i("xxx", "getSyncShopCart: userId" + userId + "sessionId" + sessionId + params);

        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);

        apiService.getSyncShopCar(Integer.parseInt(userId), sessionId, RequestBody.create(MediaType.parse("text/plain"), data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SyncShopCarBean>() {
                    @Override
                    public void onNext(SyncShopCarBean syncShopCarBean) {
                        // Log.i("xxx", "onNext: " + syncShopCarBean.getStatus());
                        if (syncShopCarListener != null) {

                            syncShopCarListener.onResult(syncShopCarBean);
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

    public interface onDetailsListener {
        void onResult(ShopDetails.ResultBean result);
    }

    public onDetailsListener detailsListener;

    public void setDetailsListener(onDetailsListener detailsListener) {
        this.detailsListener = detailsListener;
    }

    public interface onQueryCarListener {
        void onResult(QueryCartBean queryCartBean);
    }

    public onQueryCarListener queryCarListener;

    public void setQueryCarListener(onQueryCarListener queryCarListener) {
        this.queryCarListener = queryCarListener;
    }

    public interface onSyncShopCarListener {
        void onResult(SyncShopCarBean syncShopCarBean);
    }

    public onSyncShopCarListener syncShopCarListener;

    public void setSyncShopCarListener(onSyncShopCarListener syncShopCarListener) {
        this.syncShopCarListener = syncShopCarListener;
    }

}
