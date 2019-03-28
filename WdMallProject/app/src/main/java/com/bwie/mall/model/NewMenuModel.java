package com.bwie.mall.model;

import com.bwie.mall.api.ApiService;
import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.NewMenuBean;
import com.bwie.mall.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 10:24:23
 * @Description:
 */
public class NewMenuModel {
    public void getAddress(String userId, String sessionId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.getAddress(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MineAddressBean>() {
                    @Override
                    public void onNext(MineAddressBean mineAddressBean) {
                        // Log.i("xxx", "onNext: "+mineAddressBean.getMessage());
                        if (addressListener != null) {
                            addressListener.onAddress(mineAddressBean);
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

    public void createMenu(String userId, String sessionId, String data, double sumPrice, int addressId) {
        ApiService apiService = RetrofitUtils.getInstance().setCreate(ApiService.class);
        apiService.createMenu(Integer.parseInt(userId), sessionId, data, sumPrice, addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<NewMenuBean>() {
                    @Override
                    public void onNext(NewMenuBean menuBean) {
//                        Log.i("xxx", "onNext: " + syncShopCarBean.getMessage());
                        if (createMenuListener!=null){
                            createMenuListener.onCreate(menuBean);
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

    public interface onAddressListener {
        void onAddress(MineAddressBean mineAddressBean);
    }

    public onAddressListener addressListener;

    public void setAddressListener(onAddressListener addressListener) {
        this.addressListener = addressListener;
    }


    public interface onCreateMenuListener {
        void onCreate(NewMenuBean syncShopCarBean);
    }

    public onCreateMenuListener createMenuListener;

    public void setCreateMenuListener(onCreateMenuListener createMenuListener) {
        this.createMenuListener = createMenuListener;
    }
}
