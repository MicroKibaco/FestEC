package com.asiainfo.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.app.AccountManager;
import com.asiainfo.latte_ec.database.DatabaseManager;
import com.asiainfo.latte_ec.database.UserProfile;

/**
 * 登录/注册数据解析器
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final Long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

        DatabaseManager.getInstance().getDao().insert(profile);

        // 已经注册表示登录成功
        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener listener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final Long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

        DatabaseManager.getInstance().getDao().insert(profile);

        // 已经注册表示登录成功
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();


    }
}
