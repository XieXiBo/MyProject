package com.bwie.mall.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.mall.R;
import com.bwie.mall.activity.LoginActivity;
import com.bwie.mall.activity.MineAddressActivity;
import com.bwie.mall.activity.MineCircleActivity;
import com.bwie.mall.activity.MineFootActivity;
import com.bwie.mall.activity.MineInfoActivity;
import com.bwie.mall.activity.MineWalletActivity;
import com.leon.lib.settingview.LSettingItem;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 15:33:18
 * @Description:
 */
public class MineFragment extends TBaseFragment {
    @BindView(R.id.my_icon)
    CircleImageView myIcon;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_information)
    LSettingItem myInformation;
    @BindView(R.id.my_circle)
    LSettingItem myCircle;
    @BindView(R.id.my_foot)
    LSettingItem myFoot;
    @BindView(R.id.my_wallet)
    LSettingItem myWallet;
    @BindView(R.id.my_address)
    LSettingItem myAddress;
    Unbinder unbinder;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private String nickName;
    private String headPic;

    @Override
    public void onStart() {
        super.onStart();
        sp_login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        nickName = sp_login.getString("nickName", null);
        headPic = sp_login.getString("headPic", null);
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            Glide.with(getActivity()).load(headPic).into(myIcon);
            myName.setText(nickName);
        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        sp_login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
        nickName = sp_login.getString("nickName", null);
        headPic = sp_login.getString("headPic", null);
        //判断参数不为空，登陆成功，修改头像和昵称
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            Glide.with(getActivity()).load(headPic).into(myIcon);
            myName.setText(nickName);
        }
    }

    @Override
    public void initData() {
        /**
         * 点击个人资料
         */
        myInformation.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
                    startActivity(new Intent(getActivity(), MineInfoActivity.class));
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 我的圈子
         */
        myCircle.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
                    startActivity(new Intent(getActivity(), MineCircleActivity.class));
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 我的足迹
         */
        myFoot.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
                    startActivity(new Intent(getActivity(), MineFootActivity.class));
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 我的钱包
         */
        myWallet.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
                    startActivity(new Intent(getActivity(), MineWalletActivity.class));
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 点击我的地址
         */
        myAddress.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
                    startActivity(new Intent(getActivity(), MineAddressActivity.class));
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 条目点击事件
     */
    @OnClick({R.id.my_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_icon:
                if (TextUtils.isEmpty(sessionId) && TextUtils.isEmpty(userId)) {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Toast.makeText(getActivity(), "已登录", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
