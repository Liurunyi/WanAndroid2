package com.example.wanandroid.data.entity.okhttp.LoginRepository;

import android.text.TextUtils;

import com.example.wanandroid.base.BaseRepository;
import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.data.entity.HttpResult;
import com.example.wanandroid.data.entity.User;
import com.example.wanandroid.login.LoginContract;
import com.example.wanandroid.okhttp.ApiService;
import com.example.wanandroid.okhttp.WADataService;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.w3c.dom.Text;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * created by taofu on 2019-06-11
 **/
public class LoginRepository extends BaseRepository implements LoginContract.ILoginSource {

    private ApiService mApiService;


    public LoginRepository(){
        mApiService = WADataService.getService();
    }
    @Override
    public void register(LifecycleProvider provider, Map<String, String> params, final IBaseCallBack<User> callBack) {


        request(provider, mApiService.register(params), callBack);
    }

    @Override
    public void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        request(provider, mApiService.login(params), callBack);
    }



    private void request(LifecycleProvider provider, Observable<HttpResult<User>> observable, IBaseCallBack<User> callBack){


        observer(provider, observable,new Function<HttpResult<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResult<User> userHttpResult) throws Exception {
                if(userHttpResult.errorCode == 0 && userHttpResult.data != null){
                    return Observable.just(userHttpResult.data);
                }

                String msg = "服务器返回数据为空";
                if(!TextUtils.isEmpty(userHttpResult.errorMsg)){
                    msg = userHttpResult.errorMsg;
                }

                return Observable.error(new NullPointerException(msg));
            }
        },callBack);
    }
}
