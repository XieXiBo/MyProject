package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mall.R;
import com.bwie.mall.bean.MineFootBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/30 16:01:30
 * @Description:
 */
public class MineFootAdapter extends RecyclerView.Adapter<MineFootAdapter.MyViewHolder> {
    private Context context;
    private List<MineFootBean.ResultBean>list;
    List<Integer> heights = new ArrayList<>();
    public MineFootAdapter(Context context, List<MineFootBean.ResultBean> list) {
        this.context = context;
        this.list = list;

        for (int i = 0; i < list.size(); i++) {
            int x=new Random().nextInt(200) + 200;
            heights.add(x);
        }
    }

    @NonNull
    @Override
    public MyViewHolder  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_foot, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ViewGroup.LayoutParams layoutParams = myViewHolder.img.getLayoutParams();
        layoutParams.height = heights.get(i);
        myViewHolder.img.setLayoutParams(layoutParams);
        if ( myViewHolder.img != null) {
            Glide.with(context).load(list.get(i).getMasterPic()).into(myViewHolder.img);
        }
        //绑定数据

        myViewHolder.tilte.setText(list.get(i).getCommodityName());
        myViewHolder.price.setText("¥"+list.get(i).getPrice());
        myViewHolder.num.setText("已浏览"+list.get(i).getBrowseNum()+"次");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myViewHolder.time.setText(simpleDateFormat.format(list.get(i).getBrowseNum()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tilte;
        private final TextView price;
        private final TextView num;
        private final TextView time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_foot);
            tilte = itemView.findViewById(R.id.title_foot);
            price = itemView.findViewById(R.id.price_foot);
            num = itemView.findViewById(R.id.num_foot);
            time = itemView.findViewById(R.id.time_foot);
        }
    }
}
