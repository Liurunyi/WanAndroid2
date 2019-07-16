package com.example.bannerlibrary;

import android.widget.ImageView;

    public interface IJBannerAdapter<M> {

    void fillBannerItemData(JBanner banner, ImageView imageView, M mode, int position);
}
