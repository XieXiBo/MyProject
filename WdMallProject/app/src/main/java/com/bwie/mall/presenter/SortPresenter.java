package com.bwie.mall.presenter;

import com.bwie.mall.bean.FirstCategory;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.bean.UserWallet;
import com.bwie.mall.model.HomeModel;
import com.bwie.mall.model.SortModel;
import com.bwie.mall.view.SortView;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/29 16:52:01
 * @Description:
 */
public class SortPresenter extends BasePresenter<SortView> {

    private final SortModel sortModel;
    private final SortView sortView;

    public SortPresenter(SortView view) {
        sortView = view;
        sortModel = new SortModel();
    }

    public void onSearch(String keyword, int page) {
        sortModel.getSearch(keyword, page);
        sortModel.setSearchResult(new SortModel.onSearchResult() {
            @Override
            public void onResult(List<SearchBean.ResultBean> result) {
                sortView.getSearchViewData(result);
            }
        });


    }

    //一级分类
    public void getFirst() {
        sortModel.getFirst();

        sortModel.setOnFirstListener(new SortModel.OnFirstListener() {
            @Override
            public void FirstData(List<FirstCategory.ResultEntity> result) {
                sortView.getFirstViewData(result);
            }
        });
    }

    public void getUserWallet(String id) {
        sortModel.getUserWallet(id);

        sortModel.setOnuserWalletListener(new SortModel.OnuserWalletListener() {
            @Override
            public void userWalletData(List<UserWallet.ResultEntity> result) {
                sortView.getSecondViewData(result);
            }
        });
    }

    public void getFormSecondData(String id) {
        sortModel.getFormSecondData(id);
        sortModel.setFromSecond(new SortModel.onFromSecond() {
            @Override
            public void onResult(List<SearchBean.ResultBean> result) {
                sortView.getFormSecondViewData(result);
            }
        });
    }
}
