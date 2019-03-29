package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.FirstCategory;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 20:46:15
 * @Description:
 */
public class HomeModel {
    public void getBanner() {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {

                        if (banner != null) {
                            banner.onResult(bannerBean);
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

    public void getShow() {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getShowGoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<GoodsBean>() {
                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        GoodsBean.ResultBean result = goodsBean.getResult();
                        if (showGoods != null) {
                            showGoods.onResult(result);
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

    public void getFirst() {
    }


    /**
     * 定义接口回调
     */
    public interface onBanner {
        void onResult(BannerBean bannerBean);
    }

    public interface onShowGoods {
        void onResult(GoodsBean.ResultBean result);
    }

    public void setShowGoods(onShowGoods showGoods) {
        this.showGoods = showGoods;
    }

    public onBanner banner;
    public onShowGoods showGoods;


    public void setBanner(onBanner banner) {
        this.banner = banner;
    }



}
