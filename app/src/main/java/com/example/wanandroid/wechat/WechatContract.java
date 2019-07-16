package com.example.wanandroid.wechat;

import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.Wechat;
import com.example.wanandroid.data.entity.repositories.HomeRepository;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

public interface WechatContract {
    //tablayout列表
    interface IWechatView extends IBaseView<IWechatPresenter> {
        void onWechatData(List<Wechat> wechat, String msg);
    }

    interface IWechatPresenter extends IBasePresenter<IWechatView> {
        void getWechat();
    }

    interface IWechatModel {
        void getWechat(LifecycleProvider provider, IBaseCallBack<List<Wechat>> callBack);
    }


    //viewpager列表
    interface IWechat_pagerView extends IBaseView<IWechat_pagerPresenter> {
        void onWechat_pagerData(ArticleData articleData, String msg,int page,int id);
    }

    interface IWechat_pagerPresenter extends IBasePresenter<IWechat_pagerView> {
        void getWechats_pager(int page,int id);
    }

    interface IWechat_pagerModel {
        void getWechat_pager(LifecycleProvider provider, IBaseCallBack<ArticleData> callBack, int page, int id);
    }
}
