package com.asiainfo.latte.app;

/**
 * 用户登录状态回调
 */

public interface IUserChecker {
    void onSignIn();

    void onNotSignIn();
}
