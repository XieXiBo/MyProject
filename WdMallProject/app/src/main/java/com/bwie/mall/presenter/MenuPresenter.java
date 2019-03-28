package com.bwie.mall.presenter;

import com.bwie.mall.fragment.MenuFragment;
import com.bwie.mall.model.MenuModel;
import com.bwie.mall.view.MenuView;

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
}
