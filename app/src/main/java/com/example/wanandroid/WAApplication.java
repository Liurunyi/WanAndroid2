package com.example.wanandroid;

import android.app.Application;
import android.content.Context;

import com.example.wanandroid.greendaodemo.db.DaoMaster;
import com.example.wanandroid.greendaodemo.db.DaoSession;

/*
 * created by taofu on 2019-06-11
 **/
public class WAApplication extends Application {

    public static Application mApplicationContext;

    public static boolean mIsLogin = false;

    public static Application getmApplicationContext() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }
}
