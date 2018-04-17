package com.asiainfo.latte_ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.asiainfo.latte_core.ui.loader.LatteLoader;
import com.asiainfo.latte_core.util.log.LatteLogger;

/**
 * 支付
 */

public class PayAsyncTask extends AsyncTask<String, Void, String> {

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //支付网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";
    private final Activity ACTIVITY;
    private final IAlPayResultListener LISTENER;

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(alPaySign, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        LatteLoader.stopLoading();
        final PayResult payResult = new PayResult(result);

        // 支付宝返回此次支付结构及加签，建议对支付宝签名信息拿签约是支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        LatteLogger.e("AL_PAY_RESULT", resultInfo);
        LatteLogger.e("AL_PAY_RESULT", resultStatus);

        if (LISTENER == null) {
            return;
        }
        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                LISTENER.onPaySuccess();
                break;
            case AL_PAY_STATUS_FAIL:
                LISTENER.onPayFail();
                break;
            case AL_PAY_STATUS_PAYING:
                LISTENER.onPaying();
                break;
            case AL_PAY_STATUS_CANCEL:
                LISTENER.onPayCancel();
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                LISTENER.onPayConnectError();
                break;
            default:
                break;

        }
    }
}
