package com.example.wanandroid.sourse.local.remote;

import com.example.wanandroid.base.ServerException;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.Banner;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.home.HomeContract;
import com.example.wanandroid.okhttp.WADataService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/*
 * created by taofu on 2019-06-28
 **/
public class HomeRemoteSource extends HomeContract.IHomeDataSource {


    @Override
    public Observable<List<Banner>> getBanner() {

        return WADataService.getService().getBanners().flatMap(new Function<HttpResult<List<Banner>>, ObservableSource<List<Banner>>>() {
            @Override
            public ObservableSource<List<Banner>> apply(HttpResult<List<Banner>> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {


                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        });
    }

    @Override
    public Observable<List<ArticleData.Article>> getTopArticles() {
        return WADataService.getService().getTopArticles().flatMap(new Function<HttpResult<List<ArticleData.Article>>, ObservableSource<List<ArticleData.Article>>>() {
            @Override
            public ObservableSource<List<ArticleData.Article>> apply(HttpResult<List<ArticleData.Article>> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        });
    }

    @Override
    public Observable<ArticleData> getArticles() {
        return loadMoreArticles(0);
    }

    @Override
    public Observable<ArticleData> loadMoreArticles(int page) {
        return WADataService.getService().getArticleData(page).flatMap(new Function<HttpResult<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResult<ArticleData> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.getDatas() != null && listHttpResult.data.getDatas().size() > 0) {
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        });
    }


}
