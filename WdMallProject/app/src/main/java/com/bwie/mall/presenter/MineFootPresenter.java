package com.bwie.mall.presenter;

import com.bwie.mall.activity.MineFootActivity;
import com.bwie.mall.bean.MineFootBean;
import com.bwie.mall.model.MineFootModel;
import com.bwie.mall.view.MineFootView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 15:29:32
 * @Description:
 */
public class MineFootPresenter extends BasePresenter<MineFootView> {

    private final MineFootView mineFootView;
    private final MineFootModel mineFootModel;

    public MineFootPresenter(MineFootView view) {
        mineFootView = view;
        mineFootModel = new MineFootModel();
    }

    public void onRelated(String userId, String sessionId, int page) {
        mineFootModel.getFoot(userId,sessionId,page);
        mineFootModel.setFootListener(new MineFootModel.onFootListener() {
            @Override
            public void onResult(MineFootBean mineFootBean) {
                mineFootView.getFootViewData(mineFootBean);
            }
        });
    }
}
