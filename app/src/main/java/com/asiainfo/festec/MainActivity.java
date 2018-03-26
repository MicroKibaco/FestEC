package com.asiainfo.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.asiainfo.latte_core.activitys.ProxyActivity;
import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.laucher.ILauncherListener;
import com.asiainfo.latte_ec.laucher.LauncherDelegate;
import com.asiainfo.latte_ec.laucher.OnLauncherFinishTag;
import com.asiainfo.latte_ec.main.EcBottomDelegate;
import com.asiainfo.latte_ec.sign.ISignListener;
import com.asiainfo.latte_ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

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
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
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
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            default:
                break;

        }
    }
}
