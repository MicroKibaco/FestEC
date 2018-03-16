package com.asiainfo.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.asiainfo.latte_annotations.activitys.ProxyActivity;
import com.asiainfo.latte_annotations.delegates.LatteDelegate;
import com.asiainfo.latte_annotations.ec.laucher.ILauncherListener;
import com.asiainfo.latte_annotations.ec.laucher.LauncherDelegate;
import com.asiainfo.latte_annotations.ec.laucher.OnLauncherFinishTag;
import com.asiainfo.latte_annotations.ec.sign.ISignListener;
import com.asiainfo.latte_annotations.ec.sign.SignInDelegate;
import com.asiainfo.latte_annotations.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, getString(R.string.sign_in_success_tip), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, getString(R.string.sign_up_success_tip), Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new SignInDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignUpDelegate());
                break;
            default:
                break;

        }
    }
}
