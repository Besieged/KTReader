package com.besieged.ktreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.besieged.ktreader.R;
import com.besieged.ktreader.model.entity.DoubanBook;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookItem extends RelativeLayout {


    @BindView(R.id.iv_douban_book)
    ImageView ivDoubanBook;
    @BindView(R.id.book_title)
    TextView bookTitle;
    @BindView(R.id.book_author)
    TextView bookAuthor;
    @BindView(R.id.book_time)
    TextView bookTime;
    @BindView(R.id.book_sorce)
    TextView bookSorce;

    private Context mContext;

    public DoubanBookItem(Context context) {
        this(context,null);
    }

    public DoubanBookItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_douban_book_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DoubanBook.BooksEntity doubanBook){
        try {
            Glide.with(mContext)
                    .load(doubanBook.images.large)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(ivDoubanBook);
            bookTitle.setText(doubanBook.title);
            bookAuthor.setText(String.format("作者:%s",doubanBook.author.get(0)));
            bookSorce.setText(String.format("出版时间:%s",doubanBook.pubdate));
            bookTime.setText(String.format("豆瓣评分:%s(%s人)",doubanBook.rating.average ,doubanBook.rating.numRaters));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
