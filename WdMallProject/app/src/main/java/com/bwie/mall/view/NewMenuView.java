package com.bwie.mall.view;

import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.NewMenuBean;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 10:22:43
 * @Description:
 */
public interface NewMenuView {
    void getNewMenuViewData(MineAddressBean mineAddressBean);

    void getCreateMenuData(NewMenuBean syncShopCarBean);
}
