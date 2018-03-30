package com.besieged.ktreader.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.besieged.ktreader.MVPBaseActivity;
import com.besieged.ktreader.R;
import com.besieged.ktreader.model.entity.DoubanBookDetail;
import com.besieged.ktreader.presenter.impl.DoubanBookDetailPresenterImpl;
import com.besieged.ktreader.viewInterface.DoubanBookDetailView;
import com.besieged.ktreader.widget.MoreTextView;
import com.besieged.ktreader.widget.MyTextView;
import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookDetailActivity extends MVPBaseActivity<DoubanBookDetailView, DoubanBookDetailPresenterImpl> implements DoubanBookDetailView {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.prograss)
    ProgressBar prograss;
    @BindView(R.id.book_name)
    TextView bookName;
    @BindView(R.id.book_subtitle)
    TextView bookSubtitle;
    @BindView(R.id.book_author)
    TextView bookAuthor;
    @BindView(R.id.book_publisher)
    TextView bookPublisher;
    @BindView(R.id.book_pubdate)
    TextView bookPubdate;
    @BindView(R.id.linear_left)
    LinearLayout linearLeft;
    @BindView(R.id.book_rating)
    TextView bookRating;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.book_numRaters)
    TextView bookNumRaters;
    @BindView(R.id.hint)
    TextView summaryHint;
    @BindView(R.id.description_view)
    TextView mBookSummary;
    @BindView(R.id.expand_view)
    ImageView expandView;
    @BindView(R.id.expandable_layout)
    LinearLayout expandableLayout;
    @BindView(R.id.summary_hint1)
    TextView summaryHint1;
    @BindView(R.id.MoreTextView)
    MoreTextView moreTextView;
    @BindView(R.id.content_linear)
    LinearLayout contentLinear;
    private int maxDescripLine = 3; //TextView默认最大展示行数
    private String mBookId;

    MoreTextView mSummaryAuthor;

    @Override
    public void onStartGetData() {
        prograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSearchSuccess(DoubanBookDetail doubanBookDetail) {
        prograss.setVisibility(View.GONE);
        bindView(doubanBookDetail);
    }

    @Override
    public void onGetDataFailed(String error) {
        prograss.setVisibility(View.GONE);
        toast(error);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    protected DoubanBookDetailPresenterImpl createPresenter() {
        return new DoubanBookDetailPresenterImpl(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_douban_book_detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        SwipeBackHelper.onCreate(this);
        initToolbar();
        initView();
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true);
    }

    private void initView(){
        mBookId = getIntent().getStringExtra("DOUBANBOOKID");
        mPresenter.getDetail(mBookId);

        mBookSummary.setMaxHeight(mBookSummary.getLineHeight() * maxDescripLine);
        expandableLayout.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                mBookSummary.clearAnimation();//消除动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = mBookSummary.getHeight();//起始高度
                int durationMillis = 200;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = mBookSummary.getLineHeight() * mBookSummary.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = mBookSummary.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        mBookSummary.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                mBookSummary.startAnimation(animation);
            }
        });
        mSummaryAuthor = new MoreTextView(this, null);
    }

    private void bindView(DoubanBookDetail doubanBookDetail) {
        toolbarLayout.setTitle(String.format("售价：%s",doubanBookDetail.price));
        Glide.with(this).load(doubanBookDetail.images.large).into(ivTitle);
        bookName.setText(doubanBookDetail.title);
        bookSubtitle.setText(doubanBookDetail.subtitle);
        bookAuthor.setText(String.format("作者：%s", doubanBookDetail.author));
        bookPublisher.setText(String.format("出版社：%s", doubanBookDetail.publisher));
        bookPubdate.setText(String.format("出版时间：%s", doubanBookDetail.pubdate));
        bookRating.setText(doubanBookDetail.rating.average);
        bookNumRaters.setText(String.format("%s人", doubanBookDetail.rating.numRaters));
        /*
         *  在OnCreate方法中定义设置的textView不会马上渲染并显示
         *  所以textview的getLineCount()获取到的值一般都为零
         *  因此使用post会在其绘制完成后来对ImageView进行显示控制
         *  而此处是在返回数据后设置。
         */
        mBookSummary.setText(doubanBookDetail.summary);
        summaryHint.setText("图书简介");
        expandView.setVisibility(mBookSummary.getLineCount()
                > maxDescripLine ? View.VISIBLE : View.GONE);
        /*
         *方法2 通过自定义View组合封装
         * 不使用xml来定义layout，直接定义一个继承LinearLayout的MoreTextView类
         * 这个类里边添加TextView和ImageView。
         */
        summaryHint1.setText("作者简介");
        moreTextView.setText(doubanBookDetail.authorIntro);
        /*
         *方法3 通过自定义View组合封装
         * 使用xml来定义layout
         */
        MyTextView myTextView = new MyTextView(DoubanBookDetailActivity.this);
        myTextView.setTextTags("标签", doubanBookDetail.tags);
        contentLinear.addView(myTextView);

        ratingbar.setRating(Float.parseFloat(doubanBookDetail.rating.average) / 2f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
