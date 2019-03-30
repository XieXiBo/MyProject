package com.bwie.mall.presenter;

import com.bwie.mall.activity.MineInfoActivity;
import com.bwie.mall.bean.MineInfoBean;
import com.bwie.mall.model.MineInfoModel;
import com.bwie.mall.view.MineInfoView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 08:13:36
 * @Description:
 */
public class MineInfoPresenter extends BasePresenter<MineInfoView> {

    private final MineInfoView mineInfoView;
    private final MineInfoModel mineInfoModel;

    public MineInfoPresenter(MineInfoView view) {
        mineInfoView = view;
        mineInfoModel = new MineInfoModel();
    }

    public void onRelated(String userId, String sessionId) {
        mineInfoModel.getMineInfo(userId,sessionId);

        mineInfoModel.setMineInfoListener(new MineInfoModel.onMineInfoListener() {
            @Override
            public void onRelated(MineInfoBean mineInfoBean) {
                mineInfoView.getMineInfoViewData(mineInfoBean);
            }
        });
    }
}
