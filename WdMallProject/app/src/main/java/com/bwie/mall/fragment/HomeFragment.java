package com.bwie.mall.fragment;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.HomeAdapter;
import com.bwie.mall.adapter.SearchAdapter;
import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.presenter.HomePresenter;
import com.bwie.mall.view.HomeView;
import com.bwie.mall.wiget.CustomSearch;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:主页 RecycleView条目嵌套
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    @BindView(R.id.search_home)
    CustomSearch search_Home;
    @BindView(R.id.rlv_home)
    RecyclerView rlv_Home;
    @BindView(R.id.rlv_search)
    XRecyclerView rlv_Search;
    @BindView(R.id.null_search)
    RelativeLayout null_Search;
    private HomeAdapter homeAdapter;
    private GoodsBean.ResultBean goods;
    private BannerBean banner;
    private String keyword;
    private int page = 1;
    private ArrayList<SearchBean.ResultBean> resultBeans;
    private Handler handler = new Handler();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        rlv_Home.setLayoutManager(new LinearLayoutManager(getActivity()));

        rlv_Search.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    protected HomePresenter getPresenter() {
        presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        presenter.onRelated();
        /**
         * 点击搜索按钮时
         */
        search_Home.setClickSearchText(new CustomSearch.onClickSearchText() {
            @Override
            public void onClickText(String text) {
                if (!TextUtils.isEmpty(text)) {
                    keyword = new String(text);
                    /**
                     * 获取网络数据
                     */
                    presenter.onSearch(keyword, page);
                } else {
                    Toast.makeText(getActivity(), "输入您要搜索的商品", Toast.LENGTH_SHORT).show();
                    rlv_Search.setVisibility(View.GONE);
                    null_Search.setVisibility(View.GONE);
                    rlv_Home.setVisibility(View.VISIBLE);
                }
            }
        });
        if (rlv_Search.getVisibility()==View.VISIBLE){
          rlv_Search.setLoadingListener(new XRecyclerView.LoadingListener() {
              @Override
              public void onRefresh() {
                  page=1;
                  presenter.onSearch(keyword,page);
                  handler.postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          rlv_Search.refreshComplete();
                      }
                  },2000);
              }

              @Override
              public void onLoadMore() {
                  page++;
                  presenter.onSearch(keyword,page);
                  handler.postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          rlv_Search.loadMoreComplete();
                      }
                  },2000);
              }
          });
        }
    }

    @Override
    public void getBannerData(BannerBean bannerBean) {
        //banner回调数据
        banner = bannerBean;
        if (banner != null) {
            homeAdapter = new HomeAdapter(getActivity(), goods, banner);
            rlv_Home.setAdapter(homeAdapter);
        }
    }

    @Override
    public void getShowData(GoodsBean.ResultBean result) {
        //主页显示数据
        goods = result;
        if (goods != null) {
            homeAdapter = new HomeAdapter(getActivity(), goods, banner);
            rlv_Home.setAdapter(homeAdapter);
        }
    }

    @Override
    public void getSearchData(List<SearchBean.ResultBean> result) {
        // Log.i("xxx", "getSearchData: "+result.size());
        //搜索商品数据
        if (page == 1) {
            resultBeans = new ArrayList<>();
        }
        resultBeans.addAll(result);
        if (resultBeans.size() != 0) {
            rlv_Home.setVisibility(View.GONE);
            rlv_Search.setVisibility(View.VISIBLE);
            null_Search.setVisibility(View.GONE);
            SearchAdapter myAdapter = new SearchAdapter(getActivity(), resultBeans);
            rlv_Search.setAdapter(myAdapter);
            //分页加载
            rlv_Search.scrollToPosition(myAdapter.getItemCount() - result.size() - 1);
        } else {
            rlv_Search.setVisibility(View.GONE);
            null_Search.setVisibility(View.VISIBLE);
            //3s,返回主页
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rlv_Search.setVisibility(View.GONE);
                    null_Search.setVisibility(View.GONE);
                    rlv_Home.setVisibility(View.VISIBLE);
                    search_Home.getSearchEditContent().setText("");
                }
            }, 3000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
