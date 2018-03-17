package com.asiainfo.latte_annotations.wechat;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_annotations.net.callback.IError;
import com.asiainfo.latte_annotations.net.callback.IFailure;
import com.asiainfo.latte_annotations.net.callback.ISuccess;
import com.asiainfo.latte_annotations.net.rt.RestClient;
import com.asiainfo.latte_annotations.util.log.Lattelogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by MicroKibaco on 17/03/2018.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    /**
     * 用户登录成功后的回调
     */
    protected abstract void onSignInSuccess(String useInfo);

    /**
     * 微信发送请求到第三方应用的回调
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 第三方应用发送请求到微信的回调
     */
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        Lattelogger.e("authUrl", authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {

        RestClient
                .builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject authObj = JSONObject.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");
                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_tken=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        Lattelogger.e("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());
                    }
                })
                .build()
                .get();

    }

    /**
     * 获取用户信息
     */
    private void getUserInfo(String userInfoUrl) {

        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                        Lattelogger.e("getUserInfo", "response: " + response);

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Lattelogger.e("getUserInfo" + code, msg);
                    }
                })
                .build()
                .get();
    }
}
