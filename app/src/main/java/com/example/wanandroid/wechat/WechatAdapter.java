package com.example.wanandroid.wechat;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wanandroid.data.entity.Wechat;

import java.util.ArrayList;
import java.util.List;

public class WechatAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private List<Wechat> titles;


    public WechatAdapter(FragmentManager fm, ArrayList<Fragment> fragments, List<Wechat> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }
}
