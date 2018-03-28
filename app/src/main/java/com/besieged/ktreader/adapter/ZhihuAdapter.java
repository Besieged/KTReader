package com.besieged.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.besieged.ktreader.model.entity.ZhihuLatest;
import com.besieged.ktreader.widget.ZhihuItem;

import java.util.List;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ZhihuViewHolder> {

    private Context mContext;
    List<ZhihuLatest.StoriesEntity> mZhihuLatestList;
    private static OnItemClickListener mItemClickListener;

    public ZhihuAdapter(Context mContext, List<ZhihuLatest.StoriesEntity> mZhihuLatestList) {
        this.mContext = mContext;
        this.mZhihuLatestList = mZhihuLatestList;
    }

    @Override
    public ZhihuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZhihuItem zhihuItem = new ZhihuItem(mContext);
        return new ZhihuViewHolder(zhihuItem);
    }

    @Override
    public void onBindViewHolder(final ZhihuViewHolder holder, int position) {
        final ZhihuLatest.StoriesEntity zhihuLatest = mZhihuLatestList.get(position);
        holder.zhihuItem.bindView(zhihuLatest);
        holder.zhihuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(zhihuLatest.id,zhihuLatest.title);
                    holder.zhihuItem.changeTextview();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mZhihuLatestList.size();
    }

    public static class ZhihuViewHolder extends RecyclerView.ViewHolder{

        public ZhihuItem zhihuItem;


        public ZhihuViewHolder(ZhihuItem itemView) {
            super(itemView);
            zhihuItem = itemView;
        }

    }
    public interface OnItemClickListener{
        void onItemClick(int id,String title);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
