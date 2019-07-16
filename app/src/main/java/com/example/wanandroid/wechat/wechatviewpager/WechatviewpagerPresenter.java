package com.example.wanandroid.wechat.wechatviewpager;

import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.wechat.WechatContract;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WechatviewpagerPresenter implements WechatContract.IWechat_pagerPresenter {

    private WechatContract.IWechat_pagerView mview;
    private final WechatviewpagerRepoitory wechatviewpagerRepoitory;

    public WechatviewpagerPresenter() {
        wechatviewpagerRepoitory = new WechatviewpagerRepoitory();
    }

    @Override
    public void getWechats_pager(final int page, final int id) {
        wechatviewpagerRepoitory.getWechat_pager((LifecycleProvider) mview, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                mview.onWechat_pagerData(data, null, page, id);
            }

            @Override
            public void onFail(String msg) {
                mview.onWechat_pagerData(null, msg, page, id);
            }
        }, page, id);
    }

    @Override
    public void attachView(WechatContract.IWechat_pagerView view) {
        mview = view;
    }

    @Override
    public void detachView(WechatContract.IWechat_pagerView view) {
        mview = null;
    }

}
