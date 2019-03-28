package com.bwie.mall.presenter;

import com.bwie.mall.activity.PayActivity;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.model.PayModel;
import com.bwie.mall.view.PayView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 21:08:07
 * @Description:
 */
public class PayPresenter extends BasePresenter<PayView> {

    private final PayModel payModel;
    private final PayView payView;

    public PayPresenter(PayView view) {
        payView = view;
        payModel = new PayModel();
    }

    public void onRelated(String userId, String sessionId, String orderId, int i) {
        payModel.goPay(userId, sessionId, orderId, i);

        payModel.setPayListener(new PayModel.onPayListener() {
            @Override
            public void onResult(SyncShopCarBean syncShopCarBean) {
                payView.getPayViewData(syncShopCarBean);
            }
        });
    }
}
