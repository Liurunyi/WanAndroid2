package com.example.wanandroid.home;

import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.Banner;
import com.example.wanandroid.data.entity.repositories.HomeRepository;
import com.example.wanandroid.data.entity.repositories.HomeRepository2;
import com.example.wanandroid.sourse.local.HomeSpLocalSource2;
import com.example.wanandroid.sourse.local.remote.HomeRemoteSource;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeMode mSource;
    private HomeContract.IHomeView mView;


    public HomePresenter(){
        mSource = new HomeRepository2(new HomeRemoteSource(),new HomeSpLocalSource2());
    }

    @Override
    public void getBanner(@HomeRepository.HomeLoadType int type) {
        mSource.getBanner((LifecycleProvider) mView, type, new IBaseCallBack<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> data) {
                if(mView != null){
                    mView.onBannerReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onBannerReceiveData(null, msg);
                }
            }
        });
    }

    @Override
    public void getTopArticles(@HomeRepository.HomeLoadType  int type) {

        mSource.getTopArticles((LifecycleProvider) mView, type, new IBaseCallBack<List<ArticleData.Article>>() {
            @Override
            public void onSuccess(List<ArticleData.Article> data) {
                if(mView != null){
                    for(ArticleData.Article article : data){
                        article.setTop(true);
                    }
                    mView.onTopArticlesReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onTopArticlesReceiveData(null, msg);
                }
            }
        });

    }

    @Override
    public void getArticles(@HomeRepository.HomeLoadType  int type) {

        mSource.getArticles((LifecycleProvider) mView, type, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                if(mView != null){
                    mView.onArticlesReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onArticlesReceiveData(null, msg);
                }
            }
        });

    }

    @Override
    public void loadMoreArticles(int page, int type) {

        mSource.loadMoreArticles((LifecycleProvider) mView,type, page, new IBaseCallBack<ArticleData>() {
            @Override
            public void onSuccess(ArticleData data) {
                if(mView != null){
                    mView.onLoadMoreArticleReceiveData(data, null);
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.onLoadMoreArticleReceiveData(null, msg);
                }
            }
        });
    }

    @Override
    public void attachView(HomeContract.IHomeView view) {
        mView = view;
    }

    @Override
    public void detachView(HomeContract.IHomeView view) {
        mView = null;
    }
}
