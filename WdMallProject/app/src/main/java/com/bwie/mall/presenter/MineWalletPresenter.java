package com.bwie.mall.presenter;

import com.bwie.mall.activity.MineWalletActivity;
import com.bwie.mall.bean.MineWalletBean;
import com.bwie.mall.model.MineWalletModel;
import com.bwie.mall.view.MineWalletView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 14:13:16
 * @Description:
 */
public class MineWalletPresenter extends BasePresenter<MineWalletView> {

    private final MineWalletView mineWalletView;
    private final MineWalletModel mineWalletModel;

    public MineWalletPresenter(MineWalletView view) {
        mineWalletView = view;
        mineWalletModel = new MineWalletModel();
    }

    public void onRelated(String userId, String sessionId, int page) {
        mineWalletModel.getWalletData(userId,sessionId,page);

        mineWalletModel.setWalletListener(new MineWalletModel.onWalletListener() {
            @Override
            public void onResult(MineWalletBean mineWalletBean) {
                mineWalletView.getWalletViewData(mineWalletBean);
            }
        });
    }
}
