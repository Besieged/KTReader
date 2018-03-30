package com.besieged.ktreader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.besieged.ktreader.BaseFragment;
import com.besieged.ktreader.R;
import com.besieged.ktreader.adapter.DoubanBookAdapter;
import com.besieged.ktreader.model.entity.DoubanBook;
import com.besieged.ktreader.presenter.impl.DoubanBookPresenterImpl;
import com.besieged.ktreader.ui.activity.DoubanBookDetailActivity;
import com.besieged.ktreader.viewInterface.DoubanBookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/29.
 */

public class DoubanBookFragment extends BaseFragment<DoubanBookView, DoubanBookPresenterImpl> implements DoubanBookView {

    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.recycleview_douban)
    RecyclerView recycleviewDouban;
    @BindView(R.id.prograss)
    ProgressBar prograss;
    @BindView(R.id.fragment_book)
    FrameLayout fragmentBook;
    Unbinder unbinder;

    private DoubanBookAdapter mDoubanBookAdapter;

    @Override
    public void onStartGetData() {
        prograss.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetSearchSuccess(DoubanBook doubanBook) {
        prograss.setVisibility(View.GONE);
        tvCount.setText(String.format("找到%s个相关结果",doubanBook.total));
        mDoubanBookAdapter.addData(doubanBook);
    }

    @Override
    public void onGetDataFailed(String error) {
        prograss.setVisibility(View.GONE);
    }

    @Override
    protected DoubanBookPresenterImpl createPresenter() {
        return new DoubanBookPresenterImpl(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban_book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init(){
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("查找图书");
        searchView.setOnQueryTextListener(onQueryTextListener);

        recycleviewDouban.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleviewDouban.setHasFixedSize(true);
        recycleviewDouban.setItemAnimator(new DefaultItemAnimator());

        mDoubanBookAdapter = new DoubanBookAdapter(getContext());
        mDoubanBookAdapter.setOnItemClickListener(onItemClickListener);
        recycleviewDouban.setAdapter(mDoubanBookAdapter);
    }

    private DoubanBookAdapter.OnItemClickListener onItemClickListener = new DoubanBookAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String id) {
            Intent intent = new Intent(getContext(), DoubanBookDetailActivity.class);
            intent.putExtra("DOUBANBOOKID",String.valueOf(id));
            startActivity(intent);
        }
    };

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mPresenter.getSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
