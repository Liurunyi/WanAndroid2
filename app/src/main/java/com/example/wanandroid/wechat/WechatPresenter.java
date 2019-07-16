package com.example.wanandroid.wechat;

import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.data.entity.Wechat;
import com.example.wanandroid.data.entity.repositories.HomeRepository;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class WechatPresenter implements WechatContract.IWechatPresenter{


    private WechatContract.IWechatView mview;
    private WechatContract.IWechatModel model;

    public WechatPresenter() {
        model = new WechatRepository();
    }

    @Override
    public void getWechat() {
        model.getWechat((LifecycleProvider) mview, new IBaseCallBack<List<Wechat>>() {
            @Override
            public void onSuccess(List<Wechat> data) {
                mview.onWechatData(data,null);
            }

            @Override
            public void onFail(String msg) {
                mview.onWechatData(null,msg);
            }
        });
    }

    @Override
    public void attachView(WechatContract.IWechatView view) {
        mview = view;
    }

    @Override
    public void detachView(WechatContract.IWechatView view) {
        mview=null;
    }
}
