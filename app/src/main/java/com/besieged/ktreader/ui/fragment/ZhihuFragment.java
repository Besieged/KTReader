package com.besieged.ktreader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.besieged.ktreader.BaseFragment;
import com.besieged.ktreader.R;
import com.besieged.ktreader.adapter.ZhihuAdapter;
import com.besieged.ktreader.presenter.impl.ZhihuPresenterImpl;
import com.besieged.ktreader.ui.activity.ZhihuDetailActivity;
import com.besieged.ktreader.viewInterface.ZhihuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/22.
 */

public class ZhihuFragment extends BaseFragment<ZhihuView, ZhihuPresenterImpl> implements ZhihuView {

    @BindView(R.id.recycle_zhihu)
    RecyclerView recycleZhihu;
    @BindView(R.id.prograss)
    ProgressBar prograss;
    Unbinder unbinder;
    private ZhihuAdapter mZhihuAdapter;


    @Override
    public void onStartGetData() {
        if (prograss != null) {
            prograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetZhihuLatestSuccess() {
        if (prograss != null) {
            prograss.setVisibility(View.GONE);
        }
        mZhihuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetMoreSuccess() {
        if (prograss != null) {
            prograss.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetDataFailed(String errMsg) {
        if (prograss != null) {
            prograss.setVisibility(View.GONE);
        }
        toast(errMsg);
    }


    @Override
    protected ZhihuPresenterImpl createPresenter() {
        return new ZhihuPresenterImpl(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        mPresenter.getLatest();
        return rootView;
    }
    private void init() {
        recycleZhihu.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleZhihu.setHasFixedSize(true);
        recycleZhihu.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recycleZhihu.setItemAnimator(new DefaultItemAnimator());
        recycleZhihu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(recyclerView)){
                    mPresenter.getMore();
                }
            }
        });
        mZhihuAdapter = new ZhihuAdapter(getContext(),mPresenter.getmZhihuLatestList());
        mZhihuAdapter.setOnItemClickListener(mOnItemClickListener);
        recycleZhihu.setAdapter(mZhihuAdapter);
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView.computeVerticalScrollExtent()+recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange()){
            return true;
        }
        return false;
    }

    private ZhihuAdapter.OnItemClickListener mOnItemClickListener = new ZhihuAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int id,String title) {
            Intent intent = new Intent(getContext(), ZhihuDetailActivity.class);
            intent.putExtra("ZHIHUID",String.valueOf(id));
            intent.putExtra("ZHIHUTITLE",title);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
