package com.asiainfo.latte_core.util.callback;

import android.support.annotation.Nullable;

public interface IGlobalCallback<T> {
    void executeCallBack(@Nullable T args);
}
