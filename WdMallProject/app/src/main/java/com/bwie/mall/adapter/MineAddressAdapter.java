package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.bean.MineAddressBean;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/27 18:13:14
 * @Description:
 */
public class MineAddressAdapter extends RecyclerView.Adapter<MineAddressAdapter.MyViewHolder> {

    private Context context;
    private List<MineAddressBean.ResultBean> result;

    public MineAddressAdapter(Context context, List<MineAddressBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(result.get(i).getRealName() + "");
        myViewHolder.phone.setText(result.get(i).getPhone() + "");
        myViewHolder.address.setText(result.get(i).getAddress() + "");
        myViewHolder.mrAddress.setChecked(result.get(i).isBoolean());
        if (result.get(i).getWhetherDefault() == 1) {
            myViewHolder.mrAddress.setChecked(true);
        }
        myViewHolder.mrAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mrAddresslistener != null) {
                    mrAddresslistener.onResult(myViewHolder.mrAddress.isChecked(), i);
                }
            }
        });

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentData!=null){
                    intentData.onResult(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView address;
        private final CheckBox mrAddress;
        private final TextView itemDelete;
        private final TextView itemUpdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_item_address);
            phone = itemView.findViewById(R.id.phone_item_address);
            address = itemView.findViewById(R.id.address_item_address);
            mrAddress = itemView.findViewById(R.id.item_mr_address);
            itemDelete = itemView.findViewById(R.id.item_delete_address);
            itemUpdate = itemView.findViewById(R.id.item_update_address);
        }
    }

    public interface onMrAddress {
        void onResult(boolean isChecked, int i);
    }

    public onMrAddress mrAddresslistener;

    public void setMrAddress(onMrAddress mrAddress) {
        this.mrAddresslistener = mrAddress;
    }

    public interface onIntentData {
        void  onResult(int i);
    }
    public onIntentData intentData;

    public void setIntentData(onIntentData intentData) {
        this.intentData = intentData;
    }
}
