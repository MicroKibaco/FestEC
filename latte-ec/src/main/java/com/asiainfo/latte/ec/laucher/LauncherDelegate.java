package com.asiainfo.latte.ec.laucher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.asiainfo.latte.app.AccountManager;
import com.asiainfo.latte.delegates.LatteDelegate;
import com.asiainfo.latte.ec.sign.SignInDelegate;
import com.asiainfo.latte.util.storage.LattePreference;
import com.asiainfo.latte.util.timer.BaseTimerTask;
import com.asiainfo.latte.util.timer.ITimerListener;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 闪屏页面倒计时
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private Timer mTimer = null;
    private int mCount = 5;

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {

        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);

    }


    // 判断是否展示欢迎页
    private void checkIsShowScroll() {

        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检查用户是否已经登录App
            if (!AccountManager.isSignIn()) {
                getSupportDelegate().start(new SignInDelegate());
            }

        }

    }


    @OnClick(R2.id.tv_launcher_timer)
    public void onClickTimerView() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }

    }
}
