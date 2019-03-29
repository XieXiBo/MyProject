package com.bwie.mall.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.bwie.mall.R;
import com.bwie.mall.adapter.CircleAdapter;
import com.bwie.mall.bean.CircleListBean;
import com.bwie.mall.presenter.CirclePresenter;
import com.bwie.mall.view.CircleView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:
 */
public class CircleFragment extends BaseFragment<CirclePresenter> implements CircleView {
    @BindView(R.id.xrlv_circle)
    XRecyclerView xrlvCircle;

    private int page = 1;
    private int count = 10;
    private Map<String, String> params;

    private Handler handler = new Handler();
    private List<CircleListBean.ResultBean> list;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;

    @Override
    public void onResume() {
        super.onResume();
        sp_login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        params = new HashMap<>();
        params.put("userId", userId);
        params.put("sessionId", sessionId);
        if (!params.isEmpty()) {
            presenter.onRelated(params, count, page);
        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_circle;
    }

    @Override
    public void initView() {
        xrlvCircle.setLayoutManager(new LinearLayoutManager(getActivity()));
        params = new HashMap<>();
        params.put("userId", userId);
        params.put("sessionId", sessionId);
    }

    @Override
    protected CirclePresenter getPresenter() {
        presenter = new CirclePresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        if (!params.isEmpty()) {
            presenter.onRelated(params, count, page);
        }
        xrlvCircle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.onRelated(params, count, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlvCircle.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.onRelated(params, count, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlvCircle.loadMoreComplete();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void getCircleData(List<CircleListBean.ResultBean> result) {
        if (result.size() != 0) {
            if (page == 1) {
                list = new ArrayList<>();
            }
            list.addAll(result);
            if (list.size() != 0) {
                CircleAdapter circleAdapter = new CircleAdapter(getActivity(), list);
                xrlvCircle.setAdapter(circleAdapter);
                //分页加载
                xrlvCircle.scrollToPosition(circleAdapter.getItemCount()-(result.size() - 1));
            }
        }
    }
}
