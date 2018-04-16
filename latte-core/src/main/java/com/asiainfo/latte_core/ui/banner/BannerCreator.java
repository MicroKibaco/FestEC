package com.asiainfo.latte_core.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.asiainfo.latte_core.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * 轮播图生成器
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> banner,
                                  ArrayList<String> banners,
                                  OnItemClickListener listener) {

        banner.setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(listener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

}
