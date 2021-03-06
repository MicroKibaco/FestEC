package com.asiainfo.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户注册
 */

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.sign_up_edit_name)
    TextInputEditText mName;
    @BindView(R2.id.sign_up_edit_email)
    TextInputEditText mEmail;
    @BindView(R2.id.sign_up_edit_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.sign_up_edit_password)
    TextInputEditText mPassword;
    @BindView(R2.id.sign_up_edit_repassword)
    TextInputEditText mRePassword;
    private ISignListener mISignListener;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    private boolean checkFrom() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError(getString(R.string.sign_up_error_name));
            isPass = false;
        } else {
            mName.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError(getString(R.string.sign_up_error_email));
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError(getString(R.string.sign_up_error_phone));
            isPass = false;
        } else {
            mPhone.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError(getString(R.string.sign_up_error_password));
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError(getString(R.string.sign_up_error_repassword));
            isPass = false;
        } else {
            mRePassword.setError(null);
        }
        return isPass;
    }

    @OnClick({R2.id.btn_sign_up})
    void onClickSignUp() {
        // 调用注册接口返回成功里面
        /**
         *  RestClient.builder()
         .url("http://192.168.0.114:8080/RestDataServer/api/user_profile.php")
         .params("name", mName.getText().toString())
         .params("email", mEmail.getText().toString())
         .params("phone", mPhone.getText().toString())
         .params("password", mPassword.getText().toString())
         .success(new ISuccess() {
        @Override public void onSuccess(String response) {
        LatteLogger.json("USER_PROFILE", response);
        SignHandler.onSignUp(response, mISignListener);
        }
        })
         .build();
         */
        if (checkFrom()) {
            mISignListener.onSignUpSuccess();
            getSupportDelegate().startWithPop(new SignInDelegate());
        }
    }

    @OnClick({R2.id.tv_link_sign_in})
    void onClickLink() {
        getSupportDelegate().startWithPop(new SignInDelegate());
    }
}
