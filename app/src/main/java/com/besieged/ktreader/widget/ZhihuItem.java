package com.besieged.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.besieged.ktreader.R;
import com.besieged.ktreader.model.entity.ZhihuLatest;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class ZhihuItem extends RelativeLayout {

    @BindView(R.id.zhihu_iv)
    ImageView zhihuIv;
    @BindView(R.id.zhihu_title)
    TextView zhihuTitle;

    private Context context;


    public ZhihuItem(Context context) {
        this(context, null);
    }

    public ZhihuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_zhihu_item, this);
        ButterKnife.bind(this);
    }
    public void changeTextview() {
        zhihuTitle.setTextColor(getResources().getColor(R.color.gray));
    }

    public void bindView(ZhihuLatest.StoriesEntity zhihuLatest) {
        zhihuTitle.setText(zhihuLatest.title);
        zhihuTitle.setTextColor(getResources().getColor(R.color.black));
        String url = zhihuLatest.images.get(0).toString();
        //Glide 获取图片
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.loading) //占位图片
                .error(R.drawable.error) //错误图片
                .into(zhihuIv);
    }

}
