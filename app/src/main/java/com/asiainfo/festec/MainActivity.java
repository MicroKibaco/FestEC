package com.asiainfo.festec;

import com.asiainfo.latte.activitys.ProxyActivity;
import com.asiainfo.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainDelegate();
    }

}
