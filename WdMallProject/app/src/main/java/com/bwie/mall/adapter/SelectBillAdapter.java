package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bwie.mall.R;
import com.bwie.mall.bean.SelectBillBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: 李
 * @Date: 2019/3/29 14:40:54
 * @Description:
 */
public class SelectBillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<SelectBillBean.OrderListBean> orderList;
    private Context context;
    private int TYPE_ONE = 1,TYPE_TWO = 2,TYPE_THREE = 3,TYPE_FOUR = 9;
    public SelectBillAdapter(List<SelectBillBean.OrderListBean> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == TYPE_ONE){
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.bill_recyclerview_item,viewGroup,false));
            }else if (i == TYPE_TWO){
                return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.bill_recyclerview_item3,viewGroup,false));
            }else if (i == TYPE_THREE){
                return new ViewHolder3(LayoutInflater.from(context).inflate(R.layout.bill_recyclerview_item4,viewGroup,false));
            }else {
                return new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.bill_recyclerview_item5,viewGroup,false));
            }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            int price = 0;
            int num = 0;
            ((ViewHolder) viewHolder).bill_fragment_orderId.setText(orderList.get(i).getOrderId());
            ((ViewHolder) viewHolder).bill_fragment_createtime.setText("2019-03-29");
            for (int j=0;j<orderList.get(i).getDetailList().size();j++){
                price += orderList.get(i).getDetailList().get(j).getCommodityPrice();
                num += orderList.get(i).getDetailList().get(j).getCommodityCount();
            }
            ((ViewHolder) viewHolder).bill_fragment_count.setText(num+"");
            ((ViewHolder) viewHolder).bill_fragment_price.setText(price+"");
            List<SelectBillBean.OrderListBean.DetailListBean> detailList = orderList.get(i).getDetailList();
            ((ViewHolder) viewHolder).bill_fragment_item_recyclerview.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
            ((ViewHolder) viewHolder).bill_fragment_item_recyclerview.setAdapter(new SelectBillItemAdapter(detailList,context,getItemViewType(i)));
        }else if (viewHolder instanceof ViewHolder2){

            ((ViewHolder2) viewHolder).bill_fragment_orderId3.setText(orderList.get(i).getOrderId());
            ((ViewHolder2) viewHolder).bill_fragment_createtime3.setText("2019-03-29");
            ((ViewHolder2) viewHolder).text_express.setText(orderList.get(i).getExpressCompName());
            ((ViewHolder2) viewHolder).text_express_num.setText(orderList.get(i).getExpressSn());
            List<SelectBillBean.OrderListBean.DetailListBean> detailList = orderList.get(i).getDetailList();
            ((ViewHolder2) viewHolder).bill_fragment_item_recyclerview3.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
            ((ViewHolder2) viewHolder).bill_fragment_item_recyclerview3.setAdapter(new SelectBillItemAdapter(detailList,context,getItemViewType(i)));
        }else if (viewHolder instanceof ViewHolder3){

            ((ViewHolder3) viewHolder).bill_fragment_orderId4.setText(orderList.get(i).getOrderId());
            ((ViewHolder3) viewHolder).bill_fragment_createtime4.setText("2019-03-29");
            List<SelectBillBean.OrderListBean.DetailListBean> detailList = orderList.get(i).getDetailList();
            ((ViewHolder3) viewHolder).bill_fragment_item_recyclerview4.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
            ((ViewHolder3) viewHolder).bill_fragment_item_recyclerview4.setAdapter(new SelectBillItemAdapter(detailList,context,getItemViewType(i)));
        }else if (viewHolder instanceof ViewHolder4){

            ((ViewHolder4) viewHolder).bill_fragment_orderId5.setText(orderList.get(i).getOrderId());
            ((ViewHolder4) viewHolder).bill_fragment_createtime5.setText("2019-03-29");
            List<SelectBillBean.OrderListBean.DetailListBean> detailList = orderList.get(i).getDetailList();
            ((ViewHolder4) viewHolder).bill_fragment_item_recyclerview5.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
            ((ViewHolder4) viewHolder).bill_fragment_item_recyclerview5.setAdapter(new SelectBillItemAdapter(detailList,context,getItemViewType(i)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (orderList.get(position).getOrderStatus()==1){
            return TYPE_ONE;
        }else if (orderList.get(position).getOrderStatus()==2){
            return TYPE_TWO;
        }else if (orderList.get(position).getOrderStatus()==3){
            return TYPE_THREE;
        }else {
            return TYPE_FOUR;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_fragment_orderId)
        TextView bill_fragment_orderId;
        @BindView(R.id.bill_fragment_createtime)
        TextView bill_fragment_createtime;
        @BindView(R.id.bill_fragment_item_recyclerview)
        RecyclerView bill_fragment_item_recyclerview;
        @BindView(R.id.bill_fragment_count)
        TextView bill_fragment_count;
        @BindView(R.id.bill_fragment_price)
        TextView bill_fragment_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_fragment_orderId3)
        TextView bill_fragment_orderId3;
        @BindView(R.id.bill_fragment_createtime3)
        TextView bill_fragment_createtime3;
        @BindView(R.id.bill_fragment_item_recyclerview3)
        RecyclerView bill_fragment_item_recyclerview3;
        @BindView(R.id.text_express)
        TextView text_express;
        @BindView(R.id.text_express_num)
        TextView  text_express_num;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_fragment_orderId4)
        TextView bill_fragment_orderId4;
        @BindView(R.id.bill_fragment_createtime4)
        TextView bill_fragment_createtime4;
        @BindView(R.id.bill_fragment_item_recyclerview4)
        RecyclerView bill_fragment_item_recyclerview4;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {

        @BindView(R.id.bill_fragment_orderId5)
        TextView bill_fragment_orderId5;
        @BindView(R.id.bill_fragment_createtime5)
        TextView bill_fragment_createtime5;
        @BindView(R.id.bill_fragment_item_recyclerview5)
        RecyclerView bill_fragment_item_recyclerview5;
        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
