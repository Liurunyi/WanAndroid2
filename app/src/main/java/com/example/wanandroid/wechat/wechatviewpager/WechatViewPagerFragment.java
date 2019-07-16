package com.example.wanandroid.wechat.wechatviewpager;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.data.entity.ArticleData;
import com.example.wanandroid.wechat.WechatContract;

import java.util.List;

/**
 *
 */
public class WechatViewPagerFragment extends BaseFragment implements WechatContract.IWechat_pagerView {

    private int mpage;
    private int mid;
    private RecyclerView mHomeRvArticleList;
    private WechatContract.IWechat_pagerPresenter mpresenter;
    private WechatViewPagerAdapter wechatViewPagerAdapter;
    private static final String TAG = "WechatViewPagerFragment";

    public WechatViewPagerFragment(int id) {
        mid = id;
        Log.d(TAG, "WechatViewPagerFragment: " + mid);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            setPresenter(new WechatviewpagerPresenter());
            mpresenter.getWechats_pager(mpage, mid);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat_view_pager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHomeRvArticleList = view.findViewById(R.id.home_rv_article_list);
        mHomeRvArticleList.setLayoutManager(new LinearLayoutManager(getContext()));

        wechatViewPagerAdapter = new WechatViewPagerAdapter(getContext());
        mHomeRvArticleList.setAdapter(wechatViewPagerAdapter);
    }

    @Override
    public void onWechat_pagerData(ArticleData articleData, String msg, int page, int id) {
        mpage = page;
        if (articleData != null ){
        Log.d(TAG, "onWechat_pagerData: " + articleData.getDatas().size());
            for (int i = 0; i < articleData.getDatas().size(); i++) {
                wechatViewPagerAdapter.addlist(articleData.getDatas());
            }
        }
    }

    @Override
    public void setPresenter(WechatContract.IWechat_pagerPresenter presenter) {
        mpresenter = presenter;
        mpresenter.attachView(this);
    }

    @Override
    public Context getContextObject() {
        return null;
    }
}
