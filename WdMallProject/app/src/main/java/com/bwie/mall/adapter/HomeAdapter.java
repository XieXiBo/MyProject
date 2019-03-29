package com.bwie.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.mall.R;
import com.bwie.mall.activity.WebActivity;
import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.GoodsBean;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * @Auther: Mac
 * @Date: 2019/3/3 13:29:01
 * @Description:
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private GoodsBean.ResultBean result;
    private BannerBean myBanner;
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private final int TYPE4 = 3;

    public HomeAdapter(Context context, GoodsBean.ResultBean result, BannerBean myBanner) {
        this.context = context;
        this.result = result;
        this.myBanner = myBanner;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE1) {
            return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.banner_home_item, viewGroup, false));
        } else if (i == TYPE2) {
            return new RxxpViewHolder(LayoutInflater.from(context).inflate(R.layout.show_home_item, viewGroup, false));
        } else if (i == TYPE3) {
            return new MlssViewHolder(LayoutInflater.from(context).inflate(R.layout.show_home_item, viewGroup, false));
        }
        return new PzshViewHoler(LayoutInflater.from(context).inflate(R.layout.show_home_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof BannerViewHolder) {
           final List<BannerBean.ResultBean> result = myBanner.getResult();
           if (result.size()!=0){
               ((BannerViewHolder) viewHolder).xBanner.setData(result, null);
               ((BannerViewHolder) viewHolder).xBanner.loadImage(new XBanner.XBannerAdapter() {
                   @Override
                   public void loadBanner(XBanner banner, Object model, View view, int position) {
                       BannerBean.ResultBean baner = (BannerBean.ResultBean) model;
                       Glide.with(context).load(baner.getImageUrl()).into((ImageView) view);
                       banner.setPageChangeDuration(1000);
                   }
               });
               //banner点击跳转
               ((BannerViewHolder) viewHolder).xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                   @Override
                   public void onItemClick(XBanner banner, Object model, View view, int position) {
                       Intent intent = new Intent(context,WebActivity.class);
                       intent.putExtra("url",result.get(position).getJumpUrl());
                       context.startActivity(intent);
                   }
               });
           }
        }
        if (viewHolder instanceof RxxpViewHolder) {
            ((RxxpViewHolder) viewHolder).text.setText(result.getRxxp().getName());
            ((RxxpViewHolder) viewHolder).text.setBackgroundResource(R.mipmap.rxxp);
            ((RxxpViewHolder) viewHolder).text.setTextColor(Color.parseColor("#fd7b23"));
            List<GoodsBean.ResultBean.RxxpBean.CommodityListBean> commodityList = result.getRxxp().getCommodityList();
           /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);*/
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            ((RxxpViewHolder) viewHolder).recyclerView.setLayoutManager(gridLayoutManager);
            RxxpAdapter myRxxpAdapter = new RxxpAdapter(context, commodityList);
            ((RxxpViewHolder) viewHolder).recyclerView.setAdapter(myRxxpAdapter);
        }
        if (viewHolder instanceof MlssViewHolder) {
            ((MlssViewHolder) viewHolder).text.setText(result.getMlss().getName());
            ((MlssViewHolder) viewHolder).text.setTextColor(Color.parseColor("#FF458CF5"));
            ((MlssViewHolder) viewHolder).text.setBackgroundResource(R.mipmap.mlss);
            List<GoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = result.getMlss().getCommodityList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
            ((MlssViewHolder) viewHolder).recyclerView.setLayoutManager(linearLayoutManager);
            MlssAdapter myMlssAdapter = new MlssAdapter(context, commodityList);
            ((MlssViewHolder) viewHolder).recyclerView.setAdapter(myMlssAdapter);
        }
        if (viewHolder instanceof PzshViewHoler) {
            ((PzshViewHoler) viewHolder).text.setText(result.getPzsh().getName());
            ((PzshViewHoler) viewHolder).text.setTextColor(Color.parseColor("#FFEC3C74"));
            ((PzshViewHoler) viewHolder).text.setBackgroundResource(R.mipmap.pzsh);
            List<GoodsBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = result.getPzsh().getCommodityList();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((PzshViewHoler) viewHolder).recyclerView.setLayoutManager(gridLayoutManager);
            PzshAdapter myPzshAdapter = new PzshAdapter(context, commodityList);
            ((PzshViewHoler) viewHolder).recyclerView.setAdapter(myPzshAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private XBanner xBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.home_show_banner);
        }
    }


    public class RxxpViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        RecyclerView recyclerView;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.home_show_name);
            recyclerView = itemView.findViewById(R.id.home_show_data);
        }
    }

    public class MlssViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        RecyclerView recyclerView;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.home_show_name);
            recyclerView = itemView.findViewById(R.id.home_show_data);
        }
    }

    public class PzshViewHoler extends RecyclerView.ViewHolder {
        TextView text;
        RecyclerView recyclerView;

        public PzshViewHoler(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.home_show_name);
            recyclerView = itemView.findViewById(R.id.home_show_data);
        }
    }


}