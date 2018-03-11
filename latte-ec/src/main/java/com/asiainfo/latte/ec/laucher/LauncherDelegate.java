package com.asiainfo.latte.ec.laucher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.asiainfo.latte.delegates.LatteDelegate;
import com.asiainfo.latte.util.timer.BaseTimerTask;
import com.asiainfo.latte.util.timer.ITimerListener;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

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
        mTimer.schedule(task, 0, 10000);

    }


    @OnClick(R2.id.tv_launcher_timer)
    private void onViewClicked() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

    }
}
