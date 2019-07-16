package com.example.wanandroid.wechat;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.IntDef;

import com.example.wanandroid.base.BaseRepository;
import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.base.ServerException;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.data.entity.Wechat;
import com.example.wanandroid.data.entity.okhttp.WADataService;
import com.example.wanandroid.data.entity.repositories.HomeRepository;
import com.example.wanandroid.utils.DBUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class WechatRepository extends BaseRepository implements WechatContract.IWechatModel {

    private static final String TAG = "WechatRepository";

    @IntDef({HomeRepository.HomeLoadType.LOAD_TYPE_LOAD, HomeRepository.HomeLoadType.LOAD_TYPE_REFRESH, HomeRepository.HomeLoadType.LOAD_TYPE_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HomeLoadType {
        int LOAD_TYPE_LOAD = 1;
        int LOAD_TYPE_REFRESH = 2;
        int LOAD_TYPE_MORE = 3;
    }

    @Override
    public void getWechat(LifecycleProvider provider, IBaseCallBack<List<Wechat>> callBack) {
        //异步加载数据库
        setAsyncTask(provider, callBack);
    }

    @SuppressLint("StaticFieldLeak")
    private void setAsyncTask(final LifecycleProvider provider, final IBaseCallBack<List<Wechat>> callBack) {
        new AsyncTask<String, Integer, List<Wechat>>() {
            @Override
            protected List<Wechat> doInBackground(String... strings) {
                List<Wechat> select = DBUtils.getDbUtils().select();
                if (select != null && select.size() > 0) {
                    return select;
                }
                return null;
            }

            @Override
            protected void onPostExecute(final List<Wechat> wechats) {
                if (wechats != null && wechats.size() > 0) {
                    callBack.onSuccess(wechats);
                } else {
                    observer(provider, WADataService.getService().getWechat(), new Function<HttpResult<List<Wechat>>, ObservableSource<List<Wechat>>>() {
                        @Override
                        public ObservableSource<List<Wechat>> apply(HttpResult<List<Wechat>> listHttpResult) throws Exception {
                            if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {
                                if (wechats != null && wechats.size() > 0) {
                                    for (Wechat wechat : wechats) {
                                        DBUtils.getDbUtils().insert(wechat);
                                    }
                                }
                                Log.d(TAG, "apply: " + listHttpResult.data.size());
                                return Observable.just(listHttpResult.data);
                            }
                            return Observable.error(new ServerException(listHttpResult.errorMsg));
                        }
                    }, callBack);
                }
            }
        }.execute();
    }
}
