package com.example.wanandroid.data.entity.repositories;

import androidx.annotation.IntDef;

import com.example.wanandroid.base.BaseRepository;
import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.base.ServerException;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.Banner;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.home.HomeContract;

import com.example.wanandroid.okhttp.WADataService;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class HomeRepository extends BaseRepository implements HomeContract.IHomeMode{

    @IntDef({HomeLoadType.LOAD_TYPE_LOAD, HomeLoadType.LOAD_TYPE_REFRESH, HomeLoadType.LOAD_TYPE_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HomeLoadType {
        int LOAD_TYPE_LOAD = 1;
        int LOAD_TYPE_REFRESH = 2;
        int LOAD_TYPE_MORE = 3;
    }

    @Override
    public void getBanner(LifecycleProvider provider, int type, IBaseCallBack<List<Banner>> callBack) {
        observer(provider, WADataService.getService().getBanners(), new Function<HttpResult<List<Banner>>, ObservableSource<List<Banner>>>() {
            @Override
            public ObservableSource<List<Banner>> apply(HttpResult<List<Banner>> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        }, callBack);
    }

    @Override
    public void getTopArticles(LifecycleProvider provider, int type, IBaseCallBack<List<ArticleData.Article>> callBack) {
        observer(provider, WADataService.getService().getTopArticles(), new Function<HttpResult<List<ArticleData.Article>>, ObservableSource<List<ArticleData.Article>>>() {
            @Override
            public ObservableSource<List<ArticleData.Article>> apply(HttpResult<List<ArticleData.Article>> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        }, callBack);
    }

    @Override
    public void getArticles(LifecycleProvider provider, int type, IBaseCallBack<ArticleData> callBack) {
        requestArticles(provider,type,0,callBack);
    }

    @Override
    public void loadMoreArticles(LifecycleProvider provider, int type,int page, IBaseCallBack<ArticleData> callBack) {
        requestArticles(provider,type,page,callBack);
    }


    public void requestArticles(LifecycleProvider provider, int type, int page, IBaseCallBack<ArticleData> callBack) {
        observer(provider, WADataService.getService().getArticleData(page), new Function<HttpResult<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResult<ArticleData> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 &&listHttpResult.data!=null&&listHttpResult.data.getDatas().size()>0){
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        }, callBack);
    }
}
