package com.bwie.mall.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.mall.R;
import com.bwie.mall.adapter.MineFootAdapter;
import com.bwie.mall.bean.MineFootBean;
import com.bwie.mall.presenter.MineFootPresenter;
import com.bwie.mall.view.MineFootView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 15:30:40
 * @Description:
 */
public class MineFootActivity extends BaseActivity<MineFootPresenter> implements MineFootView {
    @BindView(R.id.xrlv_foot)
    XRecyclerView xrlvFoot;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private int page = 1;
    private Handler handler = new Handler();
    private List<MineFootBean.ResultBean> list;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_minefoot;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        xrlvFoot.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public MineFootPresenter getPresenter() {
        presenter = new MineFootPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        //默认网络请求
        presenter.onRelated(userId, sessionId, page);
        /**
         * 刷新加载
         */
        xrlvFoot.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.onRelated(userId, sessionId, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlvFoot.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.onRelated(userId, sessionId, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlvFoot.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void getFootViewData(MineFootBean mineFootBean) {
        if (mineFootBean != null) {
            List<MineFootBean.ResultBean> result = mineFootBean.getResult();
            if (page == 1) {
                list = new ArrayList<>();
            }
            list.addAll(result);
            MineFootAdapter mineFootAdapter = new MineFootAdapter(MineFootActivity.this, list);
            xrlvFoot.setAdapter(mineFootAdapter);
            xrlvFoot.scrollToPosition(mineFootAdapter.getItemCount()-result.size()-1);
        }
    }

}
