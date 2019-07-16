package com.example.wanandroid.utils;

import com.example.wanandroid.WAApplication;
import com.example.wanandroid.data.entity.Wechat;
import com.example.wanandroid.greendaodemo.db.DaoMaster;
import com.example.wanandroid.greendaodemo.db.DaoSession;
import com.example.wanandroid.greendaodemo.db.WechatDao;

import java.util.List;

public class DBUtils {
    private static DBUtils dbUtils;
    private final WechatDao wechatDao;

    public static DBUtils getDbUtils() {
        if (dbUtils == null) {
            synchronized (DBUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DBUtils();
                }
            }
        }
        return dbUtils;
    }

    public DBUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(WAApplication.getmApplicationContext(), "wanandroid.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        wechatDao = daoSession.getWechatDao();
    }

    //去重
    private boolean redo(Wechat wechat) {
        List<Wechat> list = wechatDao.queryBuilder().where(WechatDao.Properties.Name.eq(wechat.getName())).list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
    //添加
    public void insert(Wechat wechat_db) {
        if (!redo(wechat_db)) {
            wechatDao.insertOrReplace(wechat_db);
        }
    }

    //查询
    public List<Wechat> select() {
        List<Wechat> list = wechatDao.queryBuilder().list();
        return list;
    }
    //删除
    public void deleteItem(Wechat wechat) {
        if (!redo(wechat)){
            wechatDao.delete(wechat);
        }
    }
}
