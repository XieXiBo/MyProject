package com.bwie.mall.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mall.R;
import com.bwie.mall.adapter.ShopCarAdapter;
import com.bwie.mall.bean.QueryCartBean;

import java.util.List;


/**
 * @Auther: xiexibo
 * @Date: 2019/3/20 11:36:10
 * @Description:组合控件
 */
public class GroupLayout extends LinearLayout implements View.OnClickListener {
    private int number = 1;
    private EditText num_goods;
    private ShopCarAdapter shopCarAdapter;
    private List<QueryCartBean.ResultBean> list;
    private int i;

    public GroupLayout(Context context) {
        super(context);
    }

    public GroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.group_layout, this);
        //控件
        TextView minus_goods = findViewById(R.id.minus_goods);
        TextView add_goods = findViewById(R.id.add_goods);
        num_goods = findViewById(R.id.num_goods);
        num_goods.setText(number + "");

        /**
         * 设置点击事件监听
         */
        minus_goods.setOnClickListener(this);
        add_goods.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_goods://加
                number++;
                num_goods.setText(number + "");
                list.get(i).setCount(number);
                numChangeListener.changeNum();
                shopCarAdapter.notifyItemChanged(i);
                break;
            case R.id.minus_goods://减
                if (number > 1) {
                    number--;

                } else {
                    Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
                }
                num_goods.setText(number + "");
                list.get(i).setCount(number);
                numChangeListener.changeNum();
                shopCarAdapter.notifyItemChanged(i);
                break;
        }

    }


    public void setData(ShopCarAdapter shopCarAdapter, List<QueryCartBean.ResultBean> list, int i) {
        this.list = list;
        this.shopCarAdapter = shopCarAdapter;
        this.i = i;
        number = list.get(i).getCount();
        num_goods.setText(this.number + "");
    }

    public interface onNumChangeListener {
        void changeNum();
    }

    public onNumChangeListener numChangeListener;

    public void setNumChangeListener(onNumChangeListener numChangeListener) {
        this.numChangeListener = numChangeListener;
    }
}
