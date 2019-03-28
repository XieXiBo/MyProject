package com.bwie.mall.presenter;

import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.MrAddressBean;
import com.bwie.mall.model.MineAddressModel;
import com.bwie.mall.view.MineAddressView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 17:37:11
 * @Description:
 */
public class MineAddressPresenter extends BasePresenter<MineAddressView> {

    private final MineAddressView mineAddressView;
    private final MineAddressModel mineAddressModel;

    public MineAddressPresenter(MineAddressView view) {
        mineAddressView = view;
        mineAddressModel = new MineAddressModel();
    }

    public void onRelated(String userId, String sessionId) {
        mineAddressModel.getAddress(userId, sessionId);

        mineAddressModel.setAddressListener(new MineAddressModel.onAddressListener() {
            @Override
            public void onAddress(MineAddressBean mineAddressBean) {
                mineAddressView.getMineAddressView(mineAddressBean);
            }
        });
    }

    public void serMrAddress(String userId, String sessionId, int id) {
        mineAddressModel.getMrAddress(userId, sessionId, id);

        mineAddressModel.setMrAddressBack(new MineAddressModel.onMrAddressBack() {
            @Override
            public void onResult(MrAddressBean mrAddressBean) {
                mineAddressView.getMrAddressView(mrAddressBean);
            }
        });
    }
}
