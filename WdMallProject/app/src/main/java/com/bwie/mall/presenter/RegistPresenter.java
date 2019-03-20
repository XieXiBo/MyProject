package com.bwie.mall.presenter;

import com.bwie.mall.bean.RegistBean;
import com.bwie.mall.model.RegistModel;
import com.bwie.mall.view.RegistView;

import java.util.Map;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:24:46
 * @Description:
 */
public class RegistPresenter extends BasePresenter<RegistView> {

    private final RegistView registView;
    private final RegistModel registModel;

    public RegistPresenter(RegistView view) {
        registView = view;
        registModel = new RegistModel();
    }

    public void sendParams(Map<String,String> params) {
        registModel.regist(params);

        registModel.setRegistListener(new RegistModel.onRegistListener() {
            @Override
            public void onResult(RegistBean loginBean) {
                registView.getViewData(loginBean);
            }
        });
    }
}
