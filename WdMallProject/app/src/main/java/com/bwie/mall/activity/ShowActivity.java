package com.bwie.mall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bwie.mall.R;
import com.bwie.mall.fragment.CircleFragment;
import com.bwie.mall.fragment.HomeFragment;
import com.bwie.mall.fragment.MenuFragment;
import com.bwie.mall.fragment.MineFragment;
import com.bwie.mall.fragment.ShopCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/13 19:45:37
 * @Description:主页面，fragment切换
 */
public class ShowActivity extends AppCompatActivity {
    @BindView(R.id.radiogroup_main)
    RadioGroup radioGroup;
    private FragmentManager manager;
    //主页
    private HomeFragment homeFragment;
    //圈子
    private CircleFragment circleFragment;
    //购物车
    private ShopCarFragment shopCarFragment;
    //订单
    private MenuFragment menuFragment;
    //我的
    private MineFragment mineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        //控件
        radioGroup = findViewById(R.id.radiogroup_main);
        //实例化对象
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        circleFragment = new CircleFragment();
        shopCarFragment = new ShopCarFragment();
        menuFragment = new MenuFragment();
        mineFragment = new MineFragment();
        //添加事务
        transaction.add(R.id.fragment_main, homeFragment);
        transaction.add(R.id.fragment_main, circleFragment);
        transaction.add(R.id.fragment_main, shopCarFragment);
        transaction.add(R.id.fragment_main, menuFragment);
        transaction.add(R.id.fragment_main, mineFragment);
        //控制显示隐藏
        transaction.show(homeFragment).hide(circleFragment).hide(shopCarFragment).hide(menuFragment).hide(mineFragment).commit();
        //按钮切换
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.radio1_main:
                        fragmentTransaction.show(homeFragment).hide(circleFragment).hide(shopCarFragment).hide(menuFragment).hide(mineFragment);
                        break;
                    case R.id.radio2_main:
                        fragmentTransaction.show(circleFragment).hide(homeFragment).hide(shopCarFragment).hide(menuFragment).hide(mineFragment);
                        break;
                    case R.id.radio3_main:
                        fragmentTransaction.show(shopCarFragment).hide(circleFragment).hide(homeFragment).hide(menuFragment).hide(mineFragment);
                        break;
                    case R.id.radio4_main:
                        fragmentTransaction.show(menuFragment).hide(circleFragment).hide(shopCarFragment).hide(homeFragment).hide(mineFragment);
                        break;
                    case R.id.radio5_main:
                        fragmentTransaction.show(mineFragment).hide(circleFragment).hide(shopCarFragment).hide(menuFragment).hide(homeFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }

}
