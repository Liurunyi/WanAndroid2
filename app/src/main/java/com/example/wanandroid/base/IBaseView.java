package com.example.wanandroid.base;

import android.content.Context;

import com.example.wanandroid.base.IBasePresenter;

/*
 * created by taofu on 2019/5/5
 **/
public interface IBaseView<T extends IBasePresenter> {


  void setPresenter(T presenter);

  Context getContextObject();
}
