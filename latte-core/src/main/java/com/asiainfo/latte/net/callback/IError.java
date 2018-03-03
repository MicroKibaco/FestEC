package com.asiainfo.latte.net.callback;

/**
 * 错误指令
 */

public interface IError {
    void onError(int code, String msg);
}
