package com.besieged.ktreader.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.besieged.ktreader.MVPBaseActivity;
import com.besieged.ktreader.R;
import com.besieged.ktreader.presenter.impl.SplashPresenterImpl;
import com.besieged.ktreader.viewInterface.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/21.
 */

public class SplashActivity extends MVPBaseActivity<SplashView, SplashPresenterImpl> implements SplashView {


    @BindView(R.id.iv_show_pic)
    ImageView ivShowPic;
    @BindView(R.id.tv_show_saying)
    TextView tvShowSaying;

    private int dur = 3000;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashPresenterImpl createPresenter() {
        return new SplashPresenterImpl(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //保持全屏窗口
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPresenter.loadSaying();
        startAnim(MainActivity.class);
    }

    private void startAnim(final Class act) {
        //传入一个ImageView对象,围绕X,Y进行2D缩放,由原始的大小方法到原来的1.15倍
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivShowPic, "scaleX", 1f, 1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivShowPic, "scaleY", 1f, 1.15f);
        //多个动画的协同工作
        AnimatorSet set = new AnimatorSet();
        set.setDuration(dur).play(animatorX).with(animatorY);
        set.start();
        //对动画的监听,动画结束后立马跳转到主页面上
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(act); //基类里的方法
            }
        });
    }

    @Override
    public void onGetSayingSuccess(String string) {
        tvShowSaying.setText(string);
    }

    @Override
    public void onGetSayingFailed() {
        tvShowSaying.setText(getString(R.string.default_saying));
    }
}
