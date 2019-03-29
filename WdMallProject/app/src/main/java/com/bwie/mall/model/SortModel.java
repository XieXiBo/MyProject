package com.bwie.mall.model;

import android.util.Log;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.FirstCategory;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.bean.UserWallet;
import com.bwie.mall.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/29 16:51:37
 * @Description:
 */
public class SortModel {
    public void getSearch(String keyword, int page) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        Flowable<SearchBean> search = apiService.getSearch(keyword, page, 6);
        search.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultBean> result = searchBean.getResult();
                        //  Log.i("xxx", "onNext: " + result.size());
                        if (searchResult != null) {
                            searchResult.onResult(result);
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

    //一级分类
    public void getFirst() {
        ApiService apiService1 = RetrofitUtils.getInstance().setCreate(ApiService.class);
        Flowable<FirstCategory> firstCategory = apiService1.getFirst();
        //获取到订阅者
        DisposableSubscriber<FirstCategory> disposableSubscriber = firstCategory.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<FirstCategory>() {
                    @Override
                    public void onNext(FirstCategory firstCategory) {
//                Log.i("FirstCategory",firstCategory.toString());
                        if (firstCategory!=null){
                            List<FirstCategory.ResultEntity> result = firstCategory.getResult();
                            if (onfirstListener != null) {
                                onfirstListener.FirstData(result);
                            }
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

    public void getUserWallet(String id) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        //获取订阅者
        Flowable<UserWallet> userWallet = apiService.getSecond(id);
        userWallet.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<UserWallet>() {
                    @Override
                    public void onNext(UserWallet userWallet) {
                       // Log.i("xxx", "onNext: "+userWallet.toString());
                       if (userWallet!=null){
                           List<UserWallet.ResultEntity> result = userWallet.getResult();
                           if (onuserwalletListener != null) {
                               onuserwalletListener.userWalletData(result);
                           }
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

    public void getFormSecondData(String id) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);

        Flowable<SearchBean> searchBean = apiService.getFormSecond(id,1,30);
        DisposableSubscriber<SearchBean> subscribeWith = searchBean.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultBean> result = searchBean.getResult();
//                            Log.i("searchBean",searchBean.toString());
                        if (fromSecond != null) {
                            fromSecond.onResult(result);
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

    public interface onSearchResult {
        void onResult(List<SearchBean.ResultBean> result);
    }

    public onSearchResult searchResult;

    public void setSearchResult(onSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    //定义一级分类接口
    public interface OnFirstListener {
        void FirstData(List<FirstCategory.ResultEntity> result);
    }

    private OnFirstListener onfirstListener;

    public void setOnFirstListener(OnFirstListener OnfirstListener) {
        this.onfirstListener = OnfirstListener;
    }

    //定义二级分类接口
    public interface OnuserWalletListener {
        void userWalletData(List<UserWallet.ResultEntity> result);
    }

    private OnuserWalletListener onuserwalletListener;

    public void setOnuserWalletListener(OnuserWalletListener onuserwalletListener) {
        this.onuserwalletListener = onuserwalletListener;
    }

    //定义根据二级分类请求的接口
    public  interface  onFromSecond{
        void onResult(List<SearchBean.ResultBean> result);
    }
    public onFromSecond fromSecond;

    public void setFromSecond(onFromSecond fromSecond) {
        this.fromSecond = fromSecond;
    }
}
