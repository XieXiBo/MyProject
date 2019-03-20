package com.bwie.mall.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 16:56:59
 * @Description:
 */
public class SearchAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SearchBean.ResultBean> list;


    public SearchAdapter(Context context, List<SearchBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.home_item_search, null, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = getItemViewType(i);

            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.img.setImageURI(Uri.parse(list.get(i).getMasterPic()));
            myViewHolder.title.setText(list.get(i).getCommodityName());
            myViewHolder.price.setText("¥：" + list.get(i).getPrice());

            /**
             * 点击EventBus回传值
             */
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int commodityId = list.get(i).getCommodityId();
                    EventBus.getDefault().post(commodityId+"");
                }
            });


    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView title;
        private final TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_item_search);
            title = itemView.findViewById(R.id.title_item_search);
            price = itemView.findViewById(R.id.price_item_search);
        }
    }


}
