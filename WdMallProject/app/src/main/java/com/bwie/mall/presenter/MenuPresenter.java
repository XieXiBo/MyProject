package com.bwie.mall.presenter;

import android.util.Log;

import com.bwie.mall.bean.SelectBillBean;
import com.bwie.mall.fragment.MenuFragment;
import com.bwie.mall.model.MenuModel;
import com.bwie.mall.view.MenuView;

import java.util.Map;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 19:45:23
 * @Description:
 */
public class MenuPresenter extends BasePresenter<MenuView> {

    private final MenuView menuView;
    private final MenuModel menuModel;

    public MenuPresenter(MenuView view) {
        menuView = view;
        menuModel = new MenuModel();
    }

    public void selectBill(Map<String,String> map, int i) {
        menuModel.getMenuState(map,i);

        menuModel.setMenuStateLinstener(new MenuModel.onMenuStateLinstener() {
            @Override
            public void onResult(SelectBillBean selectBillBean) {
                menuView.getMenuStateViewData(selectBillBean);
            }
        });
    }
}
