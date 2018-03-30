package com.besieged.ktreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.besieged.ktreader.model.entity.DoubanBook;
import com.besieged.ktreader.widget.DoubanBookItem;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookAdapter extends RecyclerView.Adapter<DoubanBookAdapter.DoubanBookViewHolder> {

    private Context mContext;
    private DoubanBook mDoubanBook;
    private OnItemClickListener mItemClickListener;

    public DoubanBookAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(DoubanBook doubanBook) {
        mDoubanBook = doubanBook;
        notifyDataSetChanged();
    }

    @Override
    public DoubanBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DoubanBookItem doubanBookItem = new DoubanBookItem(mContext);
        return new DoubanBookViewHolder(doubanBookItem);
    }

    @Override
    public void onBindViewHolder(DoubanBookViewHolder holder, int position) {
        final DoubanBook.BooksEntity doubanBookList = mDoubanBook.books.get(position);
        holder.doubanBookItem.bindView(doubanBookList);
        holder.doubanBookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(doubanBookList.id);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDoubanBook == null ? 0 : mDoubanBook.count;
    }
    public class DoubanBookViewHolder extends RecyclerView.ViewHolder {

        public DoubanBookItem doubanBookItem;
        public DoubanBookViewHolder(DoubanBookItem itemView) {
            super(itemView);
            doubanBookItem = itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

}
