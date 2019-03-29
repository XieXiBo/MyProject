package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mall.R;
import com.bwie.mall.bean.SelectBillBean;
import com.bwie.mall.wiget.GroupLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/29 14:41:22
 * @Description:
 */
public class SelectBillItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<SelectBillBean.OrderListBean.DetailListBean> detailList;
    private Context context;
    private int TYPE;

    public SelectBillItemAdapter(List<SelectBillBean.OrderListBean.DetailListBean> detailList, Context context, int TYPE) {
        this.detailList = detailList;
        this.context = context;
        this.TYPE = TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (TYPE == 1){
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_bill_item,viewGroup,false));
            }else if (TYPE == 2){
                return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.recyclerview_bill_item3,viewGroup,false));
            }else if (TYPE == 3){
                return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.recyclerview_bill_item4,viewGroup,false));
            }else {
                return new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.recyclerview_bill_item5,viewGroup,false));
            }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof ViewHolder){
                String commodityPic = detailList.get(i).getCommodityPic();
                List<String> list = Arrays.asList(commodityPic.split(","));
                Glide.with(context).load(list.get(0)).into(((ViewHolder) viewHolder).bill_item_img);
                ((ViewHolder) viewHolder).bill_item_name.setText(detailList.get(i).getCommodityName());
                ((ViewHolder) viewHolder).bill_item_price.setText(detailList.get(i).getCommodityPrice()+"");
                ((ViewHolder) viewHolder).bill_item_custom.setNumber(detailList.get(i).getCommodityCount());
            }else if (viewHolder instanceof ViewHolder2){
                String commodityPic = detailList.get(i).getCommodityPic();
                List<String> list = Arrays.asList(commodityPic.split(","));
                Glide.with(context).load(list.get(0)).into(((ViewHolder2) viewHolder).bill_item_img2);
                ((ViewHolder2) viewHolder).bill_item_name2.setText(detailList.get(i).getCommodityName());
                ((ViewHolder2) viewHolder).bill_item_price2.setText(detailList.get(i).getCommodityPrice()+"");
            }else if (viewHolder instanceof ViewHolder3){
                String commodityPic = detailList.get(i).getCommodityPic();
                List<String> list = Arrays.asList(commodityPic.split(","));
                Glide.with(context).load(list.get(0)).into(((ViewHolder3) viewHolder).bill_item_img3);
                ((ViewHolder3) viewHolder).bill_item_name3.setText(detailList.get(i).getCommodityName());
                ((ViewHolder3) viewHolder).bill_item_price3.setText(detailList.get(i).getCommodityPrice()+"");
            }else if (viewHolder instanceof ViewHolder4){
                String commodityPic = detailList.get(i).getCommodityPic();
                List<String> list = Arrays.asList(commodityPic.split(","));
                Glide.with(context).load(list.get(0)).into(((ViewHolder4) viewHolder).bill_item_img4);
                ((ViewHolder4) viewHolder).bill_item_name4.setText(detailList.get(i).getCommodityName());
                ((ViewHolder4) viewHolder).bill_item_price4.setText(detailList.get(i).getCommodityPrice()+"");
            }
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_item_img)
        ImageView bill_item_img;
        @BindView(R.id.bill_item_name)
        TextView bill_item_name;
        @BindView(R.id.bill_item_price)
        TextView bill_item_price;
        @BindView(R.id.bill_item_custom)
        GroupLayout bill_item_custom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_item_img2)
        ImageView bill_item_img2;
        @BindView(R.id.bill_item_name2)
        TextView bill_item_name2;
        @BindView(R.id.bill_item_price2)
        TextView bill_item_price2;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_item_img3)
        ImageView bill_item_img3;
        @BindView(R.id.bill_item_name3)
        TextView bill_item_name3;
        @BindView(R.id.bill_item_price3)
        TextView bill_item_price3;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_item_img4)
        ImageView bill_item_img4;
        @BindView(R.id.bill_item_name4)
        TextView bill_item_name4;
        @BindView(R.id.bill_item_price4)
        TextView bill_item_price4;
        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
