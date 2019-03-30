package com.bwie.mall.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.adapter.MineWalletAdapter;
import com.bwie.mall.bean.MineWalletBean;
import com.bwie.mall.presenter.MineWalletPresenter;
import com.bwie.mall.view.MineWalletView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 14:12:54
 * @Description:
 */
public class MineWalletActivity extends BaseActivity<MineWalletPresenter> implements MineWalletView {

    @BindView(R.id.wall_money)
    TextView wallMoney;
    @BindView(R.id.xrlv_wallet)
    XRecyclerView xrlvWallet;
    private int page = 1;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private List<MineWalletBean.ResultBean.DetailListBean> list;
    private Handler handler = new Handler();

    @Override
    public int getActivityLayout() {
        return R.layout.activity_minewallet;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
    }

    @Override
    public MineWalletPresenter getPresenter() {
        presenter = new MineWalletPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        //默认网络请求
        presenter.onRelated(userId, sessionId, page);
        xrlvWallet.setLayoutManager(new LinearLayoutManager(this));
        xrlvWallet.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        /**
         * 刷新加载
         */
        xrlvWallet.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.onRelated(userId, sessionId, page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlvWallet.refreshComplete();
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
                        xrlvWallet.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void getWalletViewData(MineWalletBean mineWalletBean) {
        if (mineWalletBean != null) {
            wallMoney.setText(mineWalletBean.getResult().getBalance() + "");
            List<MineWalletBean.ResultBean.DetailListBean> detailList = mineWalletBean.getResult().getDetailList();
           // Log.i("xxx", "getWalletViewData: "+detailList.toString());
            if (page == 1) {
                list = new ArrayList<>();
            }

               list.addAll(detailList);

            //适配器
            MineWalletAdapter walletAdapter = new MineWalletAdapter(MineWalletActivity.this, list);
            xrlvWallet.setAdapter(walletAdapter);
            xrlvWallet.scrollToPosition(walletAdapter.getItemCount() - detailList.size() - 1);
        }
    }

}
