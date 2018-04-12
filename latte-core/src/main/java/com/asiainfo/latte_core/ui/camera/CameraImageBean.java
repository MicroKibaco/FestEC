package com.asiainfo.latte_core.ui.camera;

import android.net.Uri;

/**
 * 存储一些中间值
 */

public class CameraImageBean {
    private static final CameraImageBean INSTANCE = new CameraImageBean();
    private Uri mPath = null;

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        this.mPath = path;
    }
}
