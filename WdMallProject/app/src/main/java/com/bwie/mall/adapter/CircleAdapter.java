package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mall.R;
import com.bwie.mall.bean.CircleListBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Mac
 * @Date: 2019/3/24 14:27:15
 * @Description:
 */
public class CircleAdapter extends XRecyclerView.Adapter<CircleAdapter.myViewHolder> {
    private Context context;
    private List<CircleListBean.ResultBean> list;

    public CircleAdapter(Context context, List<CircleListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.circle_show_item, null);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {

        Glide.with(context).load(list.get(i).getHeadPic()).into(myViewHolder.circle_show_ion);
        myViewHolder.circle_show_name.setText(list.get(i).getNickName());
        //时间转换
        Long dateLong = Long.valueOf(list.get(i).getCreateTime());
        String time = new SimpleDateFormat("yyyy-MM-dd hh:ss").format(new Date(dateLong));
        myViewHolder.circle_show_time.setText(time);

        myViewHolder.circle_show_content.setText(list.get(i).getContent());
        Glide.with(context).load(list.get(i).getImage()).into(myViewHolder.circle_show_img);
        myViewHolder.circle_show_num.setText(list.get(i).getGreatNum() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends XRecyclerView.ViewHolder {

        private final ImageView circle_show_ion;
        private final TextView circle_show_name;
        private final TextView circle_show_time;
        private final TextView circle_show_content;
        private final ImageView circle_show_img;
        private final ImageView circle_show_praise;
        private final TextView circle_show_num;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_show_ion = itemView.findViewById(R.id.circle_show_ion);
            circle_show_name = itemView.findViewById(R.id.circle_show_name);
            circle_show_time = itemView.findViewById(R.id.circle_show_time);
            circle_show_content = itemView.findViewById(R.id.circle_show_content);
            circle_show_img = itemView.findViewById(R.id.circle_show_img);
            circle_show_praise = itemView.findViewById(R.id.circle_show_praise);
            circle_show_num = itemView.findViewById(R.id.circle_show_num);
        }
    }
}
