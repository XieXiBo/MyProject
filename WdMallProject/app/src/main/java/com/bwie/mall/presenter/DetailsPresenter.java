package com.bwie.mall.presenter;

import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.model.DetailsModel;
import com.bwie.mall.view.DetailsView;

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
}
