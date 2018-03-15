package com.asiainfo.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.asiainfo.latte.app.AccountManager;
import com.asiainfo.latte.delegates.LatteDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户登录
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.sign_in_edit_email)
    TextInputEditText mEmail;
    @BindView(R2.id.sign_in_edit_password)
    TextInputEditText mPassword;

    private ISignListener mISignListener;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ButterKnife.bind(this, rootView);
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError(getString(R.string.sign_in_error_email));
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError(getString(R.string.sign_in_error_password));
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @OnClick({R2.id.btn_sign_in, R2.id.tv_link_sign_up, R2.id.icon_sign_in_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.btn_sign_in:
                Toast.makeText(getContext(), getString(R.string.sing_in_suc_tip), Toast.LENGTH_LONG).show();

                if (checkForm()) {
                    // 调用注册接口在返回成功里面

                    // 假成功，实际需要走网络请求
                    AccountManager.setSignState(true);
                    Toast.makeText(getContext(), getString(R.string.sing_in_suc_tip), Toast.LENGTH_LONG).show();
                }
                break;
            case R2.id.tv_link_sign_up:
                getSupportDelegate().start(new SignUpDelegate());
                break;
            case R2.id.icon_sign_in_wechat:

                break;
        }
    }
}
