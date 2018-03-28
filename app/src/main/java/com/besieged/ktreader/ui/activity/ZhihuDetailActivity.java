package com.besieged.ktreader.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.besieged.ktreader.MVPBaseActivity;
import com.besieged.ktreader.R;
import com.besieged.ktreader.model.entity.ZhihuDetail;
import com.besieged.ktreader.presenter.impl.ZhihuDetailPresenterImpl;
import com.besieged.ktreader.utils.WebUtil;
import com.besieged.ktreader.viewInterface.ZhihuDetailView;
import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public class ZhihuDetailActivity extends MVPBaseActivity<ZhihuDetailView, ZhihuDetailPresenterImpl> implements ZhihuDetailView {

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
    @BindView(R.id.wv_zhihu)
    WebView wvZhihu;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private String mZhihuId;
    private String mZhihuTitle;
    private Boolean isCollect;

    @Override
    protected ZhihuDetailPresenterImpl createPresenter() {
        return new ZhihuDetailPresenterImpl(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }
    private void initToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    private void initView(){
        mZhihuId = getIntent().getStringExtra("ZHIHUID");
        mZhihuTitle = getIntent().getStringExtra("ZHIHUTITLE");
        getSupportActionBar().setTitle(mZhihuTitle);
        mPresenter.getDetail(mZhihuId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"已添加至收藏夹",Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }
        });
        wvZhihu.setVerticalScrollBarEnabled(true);
        wvZhihu.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);

        WebSettings settings = wvZhihu.getSettings();
        //设置应用缓存路径，这个路径必须是可以让app写入文件的。该方法应该只被调用一次，重复调用会被无视~
        settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true); //启用应用缓存。
        settings.setDatabaseEnabled(true); //启用数据库缓存。
        settings.setDomStorageEnabled(true); //开启DOM缓存
        //用来设置WebView的缓存模式(这里使用的是 只要缓存可用就加载缓存)
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true); //设置WebView可以运行JavaScript。
        settings.setBuiltInZoomControls(true);//显示或不显示缩放按钮（wap网页不支持）。
        //指定WebView的页面布局显示形式，调用该方法会引起页面重绘
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvZhihu.setWebChromeClient(new WebChromeClient());
    }


    @Override
    public void onStartGetData() {
        prograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDetailSuccess(ZhihuDetail zhihuDetail) {
        prograss.setVisibility(View.GONE);
        toolbarLayout.setTitle(zhihuDetail.title);
        //在较为特殊的情况下，知乎日报可能将某个主题日报的站外文章推送至知乎日报首页。
        if (zhihuDetail.body == null){
            wvZhihu.loadUrl(zhihuDetail.shareUrl);
        }else {
            Glide.with(this).load(zhihuDetail.image).into(ivTitle);
            String data = WebUtil.buildHtmlWithCss(zhihuDetail.body,zhihuDetail.css);
            wvZhihu.loadDataWithBaseURL(WebUtil.BASE_URL,data,WebUtil.MIME_TYPE,WebUtil.ENCODING,null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetDetailFailed(String error) {
        prograss.setVisibility(View.GONE);
        toast(error);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        SwipeBackHelper.onPostCreate(this);
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        if (wvZhihu != null) {
            //webview内存泄露
            ((ViewGroup) wvZhihu.getParent()).removeView(wvZhihu);
            wvZhihu.destroy();
            wvZhihu = null;
        }
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_zhihu_detail;
    }
}
