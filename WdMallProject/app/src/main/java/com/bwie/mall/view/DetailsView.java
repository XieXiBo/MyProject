package com.bwie.mall.view;

import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.SyncShopCarBean;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 13:39:30
 * @Description:
 */
public interface DetailsView {

    void getDetailsData(ShopDetails.ResultBean result);
    void getQueryCarData(QueryCartBean queryCartBean);
    void getSyncShopCar(SyncShopCarBean syncShopCarBean);

}
