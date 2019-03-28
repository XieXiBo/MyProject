package com.bwie.mall.presenter;

import com.bwie.mall.activity.InsterAddressActivity;
import com.bwie.mall.bean.InsertAddressBean;
import com.bwie.mall.model.InsertAddressModel;
import com.bwie.mall.view.InsertAddressView;

import java.util.Map;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 18:23:52
 * @Description:
 */
public class InsertAddressPresenter extends BasePresenter<InsertAddressView> {

    private final InsertAddressModel insertAddressModel;
    private final InsertAddressView insertAddressView;

    public InsertAddressPresenter(InsertAddressView view) {
        insertAddressView = view;
        insertAddressModel = new InsertAddressModel();
    }

    public void onRelated(String userId, String sessionId, Map<String, String> params) {
        insertAddressModel.insertAddress(userId,sessionId,params);

        insertAddressModel.setInsertAddress(new InsertAddressModel.onInsertAddress() {
            @Override
            public void onInsert(InsertAddressBean insertAddressBean) {
                insertAddressView.getInsertAddressData(insertAddressBean);
            }
        });
    }
}
