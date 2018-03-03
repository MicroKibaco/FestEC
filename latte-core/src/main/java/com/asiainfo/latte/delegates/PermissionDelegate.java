package com.asiainfo.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public abstract class PermissionDelegate extends BaseDelegate {
    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
