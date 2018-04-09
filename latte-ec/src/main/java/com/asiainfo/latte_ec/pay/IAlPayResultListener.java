package com.asiainfo.latte_ec.pay;

/**
 * 支付监听
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();

}
