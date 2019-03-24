package com.bwie.mall.presenter;

import com.bwie.mall.bean.CircleListBean;
import com.bwie.mall.model.CircleModel;
import com.bwie.mall.view.CircleView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/24 14:10:00
 * @Description:
 */
public class CirclePresenter extends BasePresenter<CircleView> {

    private final CircleView circleView;
    private final CircleModel circleModel;

    public CirclePresenter(CircleView view) {
        circleView = view;
        circleModel = new CircleModel();
    }

    public void onRelated(Map<String,String> params, int count, int page) {
        circleModel.getCircleData(params,count,page);
        circleModel.setCircleListener(new CircleModel.onCircleListener() {
            @Override
            public void onResult(List<CircleListBean.ResultBean> result) {
                circleView.getCircleData(result);
            }
        });
    }
}
