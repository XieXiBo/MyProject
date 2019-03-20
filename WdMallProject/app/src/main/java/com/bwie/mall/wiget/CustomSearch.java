package com.bwie.mall.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bwie.mall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 16:07:41
 * @Description:自定义输入框
 */
public class CustomSearch extends LinearLayout {
    @BindView(R.id.search_left_img)
    ImageView searchLeftImg;
    @BindView(R.id.search_edit_content)
    EditText searchEditContent;
    @BindView(R.id.search_text_btu)
    TextView searchTextBtu;

    public EditText getSearchEditContent() {
        return searchEditContent;
    }

    public void setSearchEditContent(EditText searchEditContent) {
        this.searchEditContent = searchEditContent;
    }

    public CustomSearch(Context context) {
        super(context);
    }

    public CustomSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.search_custom, this);
        ButterKnife.bind(this);
        searchTextBtu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickSearchText != null) {
                    String text = searchEditContent.getText().toString();
                    clickSearchText.onClickText(text);
                }
            }
        });
    }

    public CustomSearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public interface onClickSearchText {
        void onClickText(String text);
    }

    public onClickSearchText clickSearchText;

    public void setClickSearchText(onClickSearchText clickSearchText) {
        this.clickSearchText = clickSearchText;
    }
}
