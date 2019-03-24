package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.CircleListBean;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/24 14:10:10
 * @Description:
 */
public class CircleModel {
    public void getCircleData(Map<String, String> params, int count, int page) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        Flowable<CircleListBean> circle = apiService.getCircle(params, count, page);
        circle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<CircleListBean>() {
                    @Override
                    public void onNext(CircleListBean circleList) {
                        //Log.i("xxx", "onNext: "+circleList.getMessage());
                        List<CircleListBean.ResultBean> result = circleList.getResult();
                        if (circleListener!=null){
                            circleListener.onResult(result);
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
    public interface onCircleListener{
        void onResult(List<CircleListBean.ResultBean> result);
    }
    public onCircleListener circleListener;

    public void setCircleListener(onCircleListener circleListener) {
        this.circleListener = circleListener;
    }
}
