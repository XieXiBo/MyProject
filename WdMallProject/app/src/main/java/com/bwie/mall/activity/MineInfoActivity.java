package com.bwie.mall.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.bean.MineInfoBean;
import com.bwie.mall.presenter.MineInfoPresenter;
import com.bwie.mall.view.MineInfoView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 08:13:01
 * @Description:
 */
public class MineInfoActivity extends BaseActivity<MineInfoPresenter> implements MineInfoView {
    @BindView(R.id.ion_mineinfo)
    SimpleDraweeView ionMineinfo;
    @BindView(R.id.name_mineinfo)
    TextView nameMineinfo;
    @BindView(R.id.pwd_mineinfo)
    TextView pwdMineinfo;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_mineinfo;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
    }

    @Override
    public MineInfoPresenter getPresenter() {
        presenter = new MineInfoPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(sessionId) && !TextUtils.isEmpty(userId)) {
            presenter.onRelated(userId, sessionId);
        } else {
            Toast.makeText(MineInfoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getMineInfoViewData(MineInfoBean mineInfoBean) {
        if (mineInfoBean.getStatus().equals("0000")){
            MineInfoBean.ResultBean result = mineInfoBean.getResult();
            nameMineinfo.setText(result.getNickName());
            ionMineinfo.setImageURI(Uri.parse(result.getHeadPic()));
            pwdMineinfo.setText(result.getPassword());
        }
    }

}
