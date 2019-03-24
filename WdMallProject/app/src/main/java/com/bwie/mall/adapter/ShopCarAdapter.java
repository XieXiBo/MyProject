package com.bwie.mall.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.wiget.GroupLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/5 11:47:24
 * @Description:
 */
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyViewHolder> {
    private Context context;
    private List<QueryCartBean.ResultBean> list;

    public ShopCarAdapter(Context context, List<QueryCartBean.ResultBean> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopcar, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        QueryCartBean.ResultBean resultBean = list.get(i);
        final int price = resultBean.getPrice();
        myViewHolder.title_item.setText(resultBean.getCommodityName());
        myViewHolder.price_item.setText("¥：" + price);
        myViewHolder.img_item.setImageURI(Uri.parse(list.get(i).getPic()));
        //根据我记录的状态，改变勾选
        myViewHolder.check_item.setChecked(list.get(i).isIscheck());

        //商品复选框点击
        myViewHolder.check_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setIscheck(isChecked);
                if (callbackListener != null) {
                    callbackListener.callback(list);
                }
            }
        });

        //加减器方法
        myViewHolder.group_item.setData(this, list, i);

        //商品数量改变
        myViewHolder.group_item.setNumChangeListener(new GroupLayout.onNumChangeListener() {
            @Override
            public void changeNum() {
                if (callbackListener != null) {
                    callbackListener.callback(list);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox check_item;
        private final SimpleDraweeView img_item;
        private final TextView title_item;
        private final TextView price_item;
        private GroupLayout group_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            check_item = itemView.findViewById(R.id.check_item);
            img_item = itemView.findViewById(R.id.img_item);
            title_item = itemView.findViewById(R.id.title_item);
            price_item = itemView.findViewById(R.id.price_item);
            group_item = itemView.findViewById(R.id.group_item);
        }
    }

    /**
     * 计算总价接口回调
     */
    public interface onCallbackListener {
        void callback(List<QueryCartBean.ResultBean> list);
    }

    public onCallbackListener callbackListener;

    public void setCallbackListener(onCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }
}
