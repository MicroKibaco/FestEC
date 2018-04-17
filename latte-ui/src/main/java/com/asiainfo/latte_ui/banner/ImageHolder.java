package com.asiainfo.latte_ui.banner;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.asiainfo.latte_core.app.Latte;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;


public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide
                .with(context)
                .load(data)
                .apply(Latte.getRecyclerOptions())
                .into(mImageView);
    }
}
