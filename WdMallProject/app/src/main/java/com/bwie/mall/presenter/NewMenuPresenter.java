package com.bwie.mall.presenter;

import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.NewMenuBean;
import com.bwie.mall.model.NewMenuModel;
import com.bwie.mall.view.NewMenuView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 10:23:52
 * @Description:
 */
public class NewMenuPresenter extends BasePresenter<NewMenuView> {
    private NewMenuView newMenuView;
    private final NewMenuModel newMenuModel;

    public NewMenuPresenter(NewMenuView view) {
        newMenuView = view;
        newMenuModel = new NewMenuModel();


    }

    public void onRelated(String userId, String sessionId) {
        newMenuModel.getAddress(userId, sessionId);

       newMenuModel.setAddressListener(new NewMenuModel.onAddressListener() {
           @Override
           public void onAddress(MineAddressBean mineAddressBean) {
               newMenuView.getNewMenuViewData(mineAddressBean);
           }
       });
    }

    public void submitMenu(String userId, String sessionId, String data, double sumPrice, int addressId) {
        newMenuModel.createMenu(userId, sessionId,data,sumPrice,addressId);

        newMenuModel.setCreateMenuListener(new NewMenuModel.onCreateMenuListener() {
            @Override
            public void onCreate(NewMenuBean newMenuBean) {
                newMenuView.getCreateMenuData(newMenuBean);
            }
        });
    }
}
