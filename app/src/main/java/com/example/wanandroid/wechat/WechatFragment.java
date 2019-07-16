package com.example.wanandroid.wechat;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.data.entity.Wechat;
import com.example.wanandroid.utils.DBUtils;
import com.example.wanandroid.wechat.wechatviewpager.WechatViewPagerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class WechatFragment extends BaseFragment implements WechatContract.IWechatView {

    private TabLayout mTabLayout;
    private ViewPager mVp;
    private WechatContract.IWechatPresenter mpresenter;
    private static final String TAG = "WechatFragment";
    private ImageView image_add;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(new WechatPresenter());
        mpresenter.getWechat();
    }

    @Override
    protected boolean isNeedToAddBackStack() {
        return false;
    }

    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__wechat;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout = view.findViewById(R.id.tabLayout);
        mVp = view.findViewById(R.id.vp);
        image_add = view.findViewById(R.id.image_add);
        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WechatAddFragment fragment = (WechatAddFragment) addFragment(getFragmentManager(), WechatAddFragment.class, android.R.id.content, null);

                
            }
        });
    }

    @Override
    public void onWechatData(List<Wechat> wechat, String msg) {
        if (wechat != null && wechat.size() > 0) {
            ArrayList<Fragment> fragments = new ArrayList<>();
            for (int i = 0; i < wechat.size(); i++) {
//            mTabLayout.addTab(mTabLayout.newTab().setText(wechat.get(i).getName()));
                fragments.add(new WechatViewPagerFragment(wechat.get(i).getId()));
                DBUtils.getDbUtils().insert(wechat.get(i));
            }
            WechatAdapter wechatAdapter = new WechatAdapter(getChildFragmentManager(), fragments, wechat);
            mVp.setAdapter(wechatAdapter);
            mTabLayout.setupWithViewPager(mVp);
        }
    }

    @Override
    public void setPresenter(WechatContract.IWechatPresenter presenter) {
        mpresenter = presenter;
        mpresenter.attachView(this);
    }

    @Override
    public Context getContextObject() {
        return getContext();
    }

}
