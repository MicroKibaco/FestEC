package com.asiainfo.latte_annotations.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.asiainfo.latte_annotations.app.Latte;

/**
 * Created by MicroKibaco on 05/03/2018.
 */

public class DimenUtil {
    /**
     * 得到屏幕的宽
     */
    public static int getScreenWidth() {

        final Resources res = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = res.getDisplayMetrics();
        return dm.widthPixels;

    }

    /**
     * 得到屏幕的高
     */
    public static int getScreenHeight() {

        final Resources res = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = res.getDisplayMetrics();
        return dm.heightPixels;

    }


}
