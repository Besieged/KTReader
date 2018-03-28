package com.besieged.ktreader.presenter.impl;

import com.besieged.ktreader.BasePresenter;
import com.besieged.ktreader.model.entity.ZhihuDetail;
import com.besieged.ktreader.model.impl.ZhihuDetailModelImpl;
import com.besieged.ktreader.presenter.ZhihuDetailPresenter;
import com.besieged.ktreader.presenter.listener.OnZhihuDetailListener;
import com.besieged.ktreader.viewInterface.ZhihuDetailView;

/**
 * Created with Android Studio
 * User: yuanxiaoru
 * Date: 2018/3/28.
 */

public class ZhihuDetailPresenterImpl extends BasePresenter<ZhihuDetailView> implements ZhihuDetailPresenter,OnZhihuDetailListener {

    private ZhihuDetailView zhihuDetailView;
    private ZhihuDetailModelImpl zhihuDetailModel;

    public ZhihuDetailPresenterImpl(ZhihuDetailView zhihuDetailView) {
        this.zhihuDetailView = zhihuDetailView;
        zhihuDetailModel = new ZhihuDetailModelImpl();
    }

    @Override
    public void getDetail(String id) {
        zhihuDetailView.onStartGetData();
        zhihuDetailModel.loadDetail(id,this);
    }

    @Override
    public void onLoadZhihuDetailSuccess(ZhihuDetail zhihuDetail) {
        zhihuDetailView.onGetDetailSuccess(zhihuDetail);
    }

    @Override
    public void onLoadDataError(String error) {
        zhihuDetailView.onGetDetailFailed(error);
    }
}
