package com.bwie.mall.view;

import com.bwie.mall.bean.FirstCategory;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.bean.UserWallet;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/29 16:51:47
 * @Description:
 */
public interface SortView {
    void getSearchViewData(List<SearchBean.ResultBean> result);

    void getFirstViewData(List<FirstCategory.ResultEntity> result);

    void getSecondViewData(List<UserWallet.ResultEntity> result);

    void getFormSecondViewData(List<SearchBean.ResultBean> result);
}
