package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.bean.MineWalletBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 不懂
 * @Date: 2019/3/17 14:43:13
 * @Description:
 */
public class MineWalletAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MineWalletBean.ResultBean.DetailListBean> detailList;

    public MineWalletAdapter(Context context, List<MineWalletBean.ResultBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        WalletHolder walletHolder = new WalletHolder(View.inflate(context, R.layout.item_wallet, null));
        return walletHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof WalletHolder) {
            WalletHolder walletHolder = (WalletHolder) viewHolder;
            //赋值
            walletHolder.price.setText("¥" + detailList.get(i).getAmount());
            long consumerTime = detailList.get(i).getConsumerTime();
            Date date = new Date(consumerTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(date);
            walletHolder.item.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return detailList.size() ;
    }

    private class WalletHolder extends RecyclerView.ViewHolder {

        private final TextView price;
        private final TextView item;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.wallt_price);
            item = itemView.findViewById(R.id.wallt_time);
        }
    }


}
