package com.example.wanandroid.wechat.wechatviewpager;

import android.util.Log;
import com.example.wanandroid.base.BaseRepository;
import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.base.ServerException;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.data.entity.okhttp.WADataService;
import com.example.wanandroid.wechat.WechatContract;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class WechatviewpagerRepoitory extends BaseRepository implements WechatContract.IWechat_pagerModel {

    private static final String TAG = "WechatviewpagerRepoitor";
    public void getWechat_pager(LifecycleProvider provider, IBaseCallBack<ArticleData> callBack, int page, int id) {
        observer(provider, WADataService.getService().getWechatList(id, page), new Function<HttpResult<ArticleData>, ObservableSource<ArticleData>>() {
            @Override
            public ObservableSource<ArticleData> apply(HttpResult<ArticleData> articleDataHttpResult) throws Exception {
                Log.d(TAG, "apply: "+articleDataHttpResult.data);
                if (articleDataHttpResult != null && articleDataHttpResult.data != null && articleDataHttpResult.data.getDatas().size() > 0) {
                    Log.d(TAG, "apply: "+ Observable.just(articleDataHttpResult.data));
                    return Observable.just(articleDataHttpResult.data);
                }
                Log.d(TAG, "apply: "+articleDataHttpResult.errorMsg);
                return Observable.error(new ServerException(articleDataHttpResult.errorMsg));
            }
        }, callBack);
    }
}
