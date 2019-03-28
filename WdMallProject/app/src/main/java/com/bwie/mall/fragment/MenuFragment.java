package com.bwie.mall.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bwie.mall.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:
 */
public class MenuFragment extends TBaseFragment {
    @BindView(R.id.tablayout_menu)
    TabLayout tablayoutMenu;
    @BindView(R.id.viewpager_menu)
    ViewPager viewpagerMenu;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] tabs = new String[]{"全部订单", "待付款", "待收货", "待评价", "已完成"};
    private int[] pic = new int[]{R.mipmap.common_icon_all_list_n_hdpi, R.mipmap.common_icon_pay_n_hdpi, R.mipmap.common_icon_receive_n, R.mipmap.commom_icon_comment_n_hdpi, R.mipmap.common_icon_finish_n_hdpi,};

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_menu;
    }

    @Override
    public void initView() {
        /**
         * 动态创建Fragment
         */
        for (int i = 0; i < tabs.length; i++) {
            //添加标题
            tablayoutMenu.addTab(tablayoutMenu.newTab().setText(tabs[i]));
            //创建fragment页面
            fragments.add(NewFragment.newInstance(i));
        }
        //关联
        tablayoutMenu.setupWithViewPager(viewpagerMenu, false);
        FmPagerAdapter fmPagerAdapter = new FmPagerAdapter(fragments, getActivity().getSupportFragmentManager());
        viewpagerMenu.setAdapter(fmPagerAdapter);

        for (int i = 0; i < tabs.length; i++) {
            tablayoutMenu.getTabAt(i).setText(tabs[i]).setIcon(pic[i]);
        }
    }


    @Override
    public void initData() {

    }

}
