package com.example.wanandroid.base;

import com.example.wanandroid.base.IBaseView;

/*
 * created by taofu on 2019/5/5
 **/
public interface IBasePresenter<T extends IBaseView> {

  void attachView(T view);

  void detachView(T view);


}
