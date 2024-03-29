package com.example.wanandroid.login;

import com.example.wanandroid.base.IBaseCallBack;
import com.example.wanandroid.base.IBasePresenter;
import com.example.wanandroid.base.IBaseView;
import com.example.wanandroid.data.entity.User;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

import io.reactivex.disposables.Disposable;


/*
 * created by taofu on 2019-06-11
 **/
public interface LoginContract {

  public interface ILoginView extends IBaseView<ILoginPresenter> {

    void onSuccess(User user);

    void onFail(String msg);
  }


  public interface ILoginPresenter extends IBasePresenter<ILoginView> {

    void register(String username,String password,String repassword);
    void login(String username,String password);

  }


  public interface ILoginSource{

    void register(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);

    void login(LifecycleProvider provider, Map<String,String> params, IBaseCallBack<User> callBack);

  }

}
