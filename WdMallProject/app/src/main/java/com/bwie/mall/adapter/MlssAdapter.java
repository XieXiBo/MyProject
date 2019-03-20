package com.bwie.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.activity.DetailsActivity;
import com.bwie.mall.bean.GoodsBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Auther: Mac
 * @Date: 2019/3/3 14:56:34
 * @Description:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.MyViewHolder> {
    private Context context;
    List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MlssAdapter(Context context, List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item_two, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.title.setText(list.get(i).getCommodityName());
        myViewHolder.price.setText("¥:" + list.get(i).getPrice());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(list.get(i).getMasterPic()))
                .build();
        PipelineDraweeController controller = (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(myViewHolder.img.getController())
                        .build();
        myViewHolder.img.setController(controller);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventBus传值
                int commodityId = list.get(i).getCommodityId();
                //Log.i("xxx", "onClick: "+commodityId);
                if (commodityId!=0){
                    EventBus.getDefault().postSticky(commodityId+"");
                    //获取值跳转
                    context.startActivity(new Intent(context,DetailsActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView price;
        private final TextView title;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.two_img);
            title = itemView.findViewById(R.id.two_title);
            price = itemView.findViewById(R.id.two_price);
        }
    }

}
