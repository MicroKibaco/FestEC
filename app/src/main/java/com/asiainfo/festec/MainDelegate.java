package com.asiainfo.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.asiainfo.latte.delegates.LatteDelegate;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public class MainDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
