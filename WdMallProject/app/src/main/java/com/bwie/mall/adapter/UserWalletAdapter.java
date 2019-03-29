package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.mall.R;
import com.bwie.mall.bean.UserWallet;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName UserWalletAdapter   二级
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/22/022 19:38
 */
public class UserWalletAdapter extends RecyclerView.Adapter {
    int[] image = {R.mipmap.coat, R.mipmap.fleece, R.mipmap.bottoming, R.mipmap.wear, R.mipmap.pants};
    Context context;
    List<UserWallet.ResultEntity> result;

    public UserWalletAdapter(Context context, List<UserWallet.ResultEntity> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_userwallet, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder)viewHolder).titleUserwallet.setText(result.get(i).getName());
        ((ViewHolder) viewHolder).image.setImageResource(image[i]);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onUserWallet!=null){
                    onUserWallet.getUserWallet(result.get(i).getId());
                }

//                Log.i("xxxxxxx",result.get(i).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title_userwallet)
        TextView titleUserwallet;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);
         }
    }
    public interface OnUserWallet{
        void getUserWallet(String id);
    }
    private OnUserWallet onUserWallet;

    public void setOnUserWallet(OnUserWallet onUserWallet) {
        this.onUserWallet = onUserWallet;
    }
}
