package com.bwie.mall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mall.R;
import com.bwie.mall.bean.FirstCategory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName FirstAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/22/022 17:56
 */
public class FirstAdapter extends RecyclerView.Adapter {
    Context context;
    List<FirstCategory.ResultEntity> result;

    public FirstAdapter(Context context, List<FirstCategory.ResultEntity> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.firstitem, null, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((FirstViewHolder) viewHolder).titlFirst.setText(result.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstAdapter!=null){
                    firstAdapter.onResult(result.get(i).getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titl_first)
        TextView titlFirst;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface onFirstAdapter {
        void onResult(String id);
    }
    public onFirstAdapter firstAdapter;

    public void setFirstAdapter(onFirstAdapter firstAdapter) {
        this.firstAdapter = firstAdapter;
    }
}
