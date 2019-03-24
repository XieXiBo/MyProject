package com.bwie.mall.presenter;

import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.SyncShopCarBean;
import com.bwie.mall.model.DetailsModel;
import com.bwie.mall.view.DetailsView;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 13:40:17
 * @Description:
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final DetailsView detailsView;
    private final DetailsModel detailsModel;

    public DetailsPresenter(DetailsView view) {
        detailsView = view;
        detailsModel = new DetailsModel();
    }

    public void onRelated(String commodityId) {
        detailsModel.getHttpData(commodityId);

        detailsModel.setDetailsListener(new DetailsModel.onDetailsListener() {
            @Override
            public void onResult(ShopDetails.ResultBean result) {
                detailsView.getDetailsData(result);
            }
        });
    }

    //查询购物车
    public void queryCart(String sessionId, String userId) {
        detailsModel.getQueryCart(sessionId, userId);

        /*//回调
        detailsModel.setQueryCarListener(new DetailsModel.onQueryCarListener() {
            @Override
            public void onResult(List<QueryCartBean.ResultBean> result) {
                detailsView.getQueryCarData(result);
            }
        });*/
        detailsModel.setQueryCarListener(new DetailsModel.onQueryCarListener() {
            @Override
            public void onResult(QueryCartBean queryCartBean) {
                detailsView.getQueryCarData(queryCartBean);
            }
        });
    }

    //添加购物车
    public void getSyncShopCart(String userId, String sessionId, String data) {
        detailsModel.getSyncShopCart(userId,sessionId,data);

        detailsModel.setSyncShopCarListener(new DetailsModel.onSyncShopCarListener() {
            @Override
            public void onResult(SyncShopCarBean syncShopCarBean) {
                detailsView.getSyncShopCar(syncShopCarBean);
            }
        });
    }
}
