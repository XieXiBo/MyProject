package com.bwie.mall.presenter;

import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.model.HomeModel;
import com.bwie.mall.view.HomeView;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 20:40:25
 * @Description:
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private final HomeModel homeModel;
    private final HomeView homeView;

    public HomePresenter(HomeView view) {
        homeView = view;
        homeModel = new HomeModel();
    }

    public void onRelated() {
        homeModel.getBanner();
        homeModel.setBanner(new HomeModel.onBanner() {
            @Override
            public void onResult(BannerBean bannerBean) {
                homeView.getBannerData(bannerBean);
            }

        });
        homeModel.getShow();
        homeModel.setShowGoods(new HomeModel.onShowGoods() {
            @Override
            public void onResult(GoodsBean.ResultBean result) {
                homeView.getShowData(result);
            }
        });
    }

    //根据搜索参数请求数据
    public void onSearch(String keyword, int page) {
        homeModel.getSearch( keyword,  page);

        homeModel.setSearchResult(new HomeModel.onSearchResult() {
            @Override
            public void onResult(List<SearchBean.ResultBean> result) {
                homeView.getSearchData(result);
            }
        });
    }
}
