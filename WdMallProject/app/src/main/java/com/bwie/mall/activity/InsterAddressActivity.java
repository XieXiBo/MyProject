package com.bwie.mall.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.bean.InsertAddressBean;
import com.bwie.mall.presenter.InsertAddressPresenter;
import com.bwie.mall.view.InsertAddressView;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 18:25:22
 * @Description:
 */
public class InsterAddressActivity extends BaseActivity<InsertAddressPresenter> implements InsertAddressView {
    @BindView(R.id.name_insert)
    EditText name_Insert;
    @BindView(R.id.phone_insert)
    EditText phone_Insert;
    @BindView(R.id.city_insert)
    EditText city_Insert;
    @BindView(R.id.address_insert)
    EditText address_Insert;
    @BindView(R.id.code_insert)
    EditText code_Insert;
    @BindView(R.id.save_insert)
    Button save_Insert;
    private CityPicker cityPicker;
    private String name;
    private String phone;
    private String city;
    private String address;
    private String code;
    private boolean flag;
    private SharedPreferences sp_login;
    private String sessionId;
    private String userId;
    private Map<String, String> params;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_insertaddress;
    }

    @Override
    public void initView() {
        sp_login = getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取SharedPreferences得值
        sessionId = sp_login.getString("sessionId", null);
        userId = sp_login.getString("userId", null);
    }

    @Override
    public InsertAddressPresenter getPresenter() {
        presenter = new InsertAddressPresenter(this);
        return presenter;
    }

    @Override
    public void initData() {
        city_Insert.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    initCityPicker();
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(city_Insert.getWindowToken(), 0); //强制隐藏键盘
                    cityPicker.show();
                }
                hasFocus = !hasFocus;
            }
        });
    }

    @OnClick({R.id.save_insert})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.save_insert:
                checkout();
                if (flag) {
                    //   Log.i("xxx", "onViewClicked: " + params.toString());
                    presenter.onRelated(userId, sessionId, params);
                }

                break;
        }
    }

    @Override
    public void getInsertAddressData(InsertAddressBean insertAddressBean) {
        if (insertAddressBean != null) {
            String status = insertAddressBean.getStatus();
            String message = insertAddressBean.getMessage();
            if (status.equals("0000")) {
                Toast.makeText(InsterAddressActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(InsterAddressActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkout() {
        flag = true;
        //获取输入框的值
        name = name_Insert.getText().toString();
        phone = phone_Insert.getText().toString();
        city = city_Insert.getText().toString();
        address = address_Insert.getText().toString();
        code = code_Insert.getText().toString();
        if (TextUtils.isEmpty(name)) {
            // Toast.makeText(InsterAddressActivity.this, "请填写收件人", Toast.LENGTH_SHORT).show();
            name_Insert.setHint("*请填写收件人");
            name_Insert.setHintTextColor(Color.RED);
            flag = false;
        }
        if (TextUtils.isEmpty(phone)) {
            // Toast.makeText(InsterAddressActivity.this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            phone_Insert.setHint("*请填写手机号码");
            phone_Insert.setHintTextColor(Color.RED);
            flag = false;
        }
        if (TextUtils.isEmpty(city)) {
            //  Toast.makeText(InsterAddressActivity.this, "请选择收货地址", Toast.LENGTH_SHORT).show();
            city_Insert.setHint("*请选择收货地址");
            city_Insert.setHintTextColor(Color.RED);
            flag = false;
        }
        if (TextUtils.isEmpty(address)) {
            //Toast.makeText(InsterAddressActivity.this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            address_Insert.setHint("*请填写详细地址");
            address_Insert.setHintTextColor(Color.RED);
            flag = false;
        }
        if (TextUtils.isEmpty(code)) {
            // Toast.makeText(InsterAddressActivity.this, "请填写邮政编码", Toast.LENGTH_SHORT).show();
            code_Insert.setHint("*请填写邮政编码");
            code_Insert.setHintTextColor(Color.RED);
            flag = false;
        }
        //  Log.i("xxx", "checkout: " + flag);
        if (flag) {
            //   Log.i("xxx", "checkout: " + flag);
            //创建集合  存入
            params = new HashMap<>();
            params.put("realName", name);
            params.put("phone", phone);
            params.put("address", city + address);
            params.put("zipCode", code);
        }

    }

    public void initCityPicker() {

        //滚轮文字的大小
        //滚轮文字的颜色
        //省份滚轮是否循环显示
        //城市滚轮是否循环显示
        //地区（县）滚轮是否循环显示
        //滚轮显示的item个数
        //滚轮item间距
        cityPicker = new CityPicker.Builder(InsterAddressActivity.this)
                .textSize(18)//滚轮文字的大小
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#3F51B5")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];

                city_Insert.setText(province + "  " + city + "  " + district);
                //  Log.e("xxx", city_Insert.getText().toString());
            }

            @Override
            public void onCancel() {

            }
        });
    }


}
