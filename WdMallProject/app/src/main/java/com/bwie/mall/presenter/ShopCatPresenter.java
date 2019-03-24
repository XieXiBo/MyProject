package com.bwie.mall.presenter;

import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.fragment.ShopCarFragment;
import com.bwie.mall.model.ShopCarModel;
import com.bwie.mall.view.ShopCarView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/22 19:24:09
 * @Description:
 */
public class ShopCatPresenter extends BasePresenter<ShopCarView> {

    private final ShopCarModel shopCarModel;
    private final ShopCarView shopCarView;

    public ShopCatPresenter(ShopCarView view) {
        shopCarView = view;
        shopCarModel = new ShopCarModel();
    }

    public void onRelated(String userId, String sessionId) {
        shopCarModel.getQueryCart(sessionId,userId);

        shopCarModel.setQueryCarListener(new ShopCarModel.onQueryCarListener() {
            @Override
            public void onResult(QueryCartBean queryCartBean) {
                shopCarView.getShopCarData(queryCartBean);
            }
        });
    }
}
