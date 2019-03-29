package com.bwie.mall.view;

import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.SearchBean;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 20:40:37
 * @Description:
 */
public interface HomeView {
    void getBannerData(BannerBean bannerBean);

    void getShowData(GoodsBean.ResultBean result);

}
