package com.asiainfo.festec;

import com.asiainfo.latte.activitys.ProxyActivity;
import com.asiainfo.latte.delegates.LatteDelegate;
import com.asiainfo.latte.ec.laucher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }

}
