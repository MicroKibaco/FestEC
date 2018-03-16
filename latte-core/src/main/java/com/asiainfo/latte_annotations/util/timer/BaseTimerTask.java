package com.asiainfo.latte_annotations.util.timer;

import java.util.TimerTask;

/**
 * Created by MicroKibaco on 11/3/2018.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mListener = null;

    public BaseTimerTask(ITimerListener listener) {
        this.mListener = listener;
    }

    @Override
    public void run() {
        if (mListener != null) {
            mListener.onTimer();
        }
    }
}
