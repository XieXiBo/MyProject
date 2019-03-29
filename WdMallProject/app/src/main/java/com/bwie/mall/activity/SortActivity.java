package com.bwie.mall.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.FirstAdapter;
import com.bwie.mall.adapter.SearchAdapter;
import com.bwie.mall.adapter.UserWalletAdapter;
import com.bwie.mall.bean.FirstCategory;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.bean.UserWallet;
import com.bwie.mall.presenter.SortPresenter;
import com.bwie.mall.view.SortView;
import com.bwie.mall.wiget.CustomSearch;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/29 16:51:23
 * @Description:商品展示页面
 */
public class SortActivity extends BaseActivity<SortPresenter> implements SortView {
    @BindView(R.id.search_home)
    CustomSearch search_sort;
    @BindView(R.id.rlv_search)
    XRecyclerView rlvSearch;
    @BindView(R.id.null_search)
    RelativeLayout nullSearch;
    private int page = 1;
    private String keyword = 1 + "";
    private ArrayList<SearchBean.ResultBean> resultBeans;
    private RecyclerView rlv_poptwo;
    private Handler handler = new Handler();
    List<SearchBean.ResultBean> list;
    private RecyclerView rlv_pop;
    private View view;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_sort;
    }

    @Override
    public void initView() {

        rlvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null, false);
        rlv_pop = view.findViewById(R.id.rlv_pop);
        rlv_poptwo = view.findViewById(R.id.rlv_poptwo);
    }

    @Override
    public SortPresenter getPresenter() {
        presenter = new SortPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        presenter.onSearch(keyword, page);
        //点击搜索
        search_sort.setClickSearchText(new CustomSearch.onClickSearchText() {
            @Override
            public void onClickText(String text) {
                if (!TextUtils.isEmpty(text)) {
                    keyword = new String(text);
                    /**
                     * 获取网络数据
                     */
                    presenter.onSearch(keyword, page);
                } else {
                    Toast.makeText(SortActivity.this, "输入您要搜索的商品", Toast.LENGTH_SHORT).show();
                    rlvSearch.setVisibility(View.VISIBLE);
                    nullSearch.setVisibility(View.GONE);
                }
            }
        });

        //分类点击按钮
        search_sort.setClickSearchImg(new CustomSearch.onClickSearchImg() {
            @Override
            public void onClickImg() {
                presenter.getFirst();
            }
        });

        //搜索页面可见就可刷新
        if (rlvSearch.getVisibility() == View.VISIBLE) {
            rlvSearch.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page = 1;
                    presenter.onSearch(keyword, page);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rlvSearch.refreshComplete();
                        }
                    }, 2000);
                }

                @Override
                public void onLoadMore() {
                    page++;
                    presenter.onSearch(keyword, page);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rlvSearch.loadMoreComplete();
                        }
                    }, 2000);
                }
            });
        }
    }

    @Override
    public void getSearchViewData(List<SearchBean.ResultBean> result) {
        if (page == 1) {
            resultBeans = new ArrayList<>();
        }
        resultBeans.addAll(result);
        if (resultBeans.size() != 0) {
            rlvSearch.setVisibility(View.VISIBLE);
            nullSearch.setVisibility(View.GONE);
            SearchAdapter myAdapter = new SearchAdapter(SortActivity.this, resultBeans);
            rlvSearch.setAdapter(myAdapter);
            //分页加载
            rlvSearch.scrollToPosition(myAdapter.getItemCount() - result.size() - 1);
        } else {
            rlvSearch.setVisibility(View.GONE);
            nullSearch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getFirstViewData(List<FirstCategory.ResultEntity> result) {

        //RecyclerView列表控件

        //布局管理器
        rlv_pop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rlv_pop.setBackgroundColor(Color.GRAY);
        //设置适配器
        FirstAdapter adapter = new FirstAdapter(this, result);
        if (result.size() != 0) {
            rlv_pop.setAdapter(adapter);
        }
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //这是背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //显示
        popupWindow.showAsDropDown(search_sort.findViewById(R.id.search_left_img));
        //默认选中
        presenter.getUserWallet("1001002");

        adapter.setFirstAdapter(new FirstAdapter.onFirstAdapter() {
            @Override
            public void onResult(String id) {
                presenter.getUserWallet(id);
            }
        });
    }

    @Override
    public void getSecondViewData(List<UserWallet.ResultEntity> result) {
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SortActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rlv_poptwo.setLayoutManager(linearLayoutManager);
        rlv_poptwo.setBackgroundColor(Color.GRAY);
        //设置适配器
        UserWalletAdapter adapter = new UserWalletAdapter(this, result);
        rlv_poptwo.setAdapter(adapter);
        adapter.setOnUserWallet(new UserWalletAdapter.OnUserWallet() {
            @Override
            public void getUserWallet(String id) {
                presenter.getFormSecondData(id);
            }
        });
    }

    @Override
    public void getFormSecondViewData(List<SearchBean.ResultBean> result) {
        if (result.size() == 0) {
            nullSearch.setVisibility(View.VISIBLE);
        } else {
            search_sort.setVisibility(View.VISIBLE);
            nullSearch.setVisibility(View.GONE);
            //判断
            if (page == 1) {
                list = new ArrayList<>();
            }
            list.addAll(result);
            //适配器
            rlvSearch.setAdapter(new SearchAdapter(this, list));
            //显示当前页
            rlvSearch.scrollToPosition(list.size() - (result.size() - 1));

        }
    }

   /* //获取FirstAdapter传来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getFirstId(Integer id) {
        Log.i("xxx", id+"");
//        二级商品类目
        presenter.getUserWallet(id+"");

    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
