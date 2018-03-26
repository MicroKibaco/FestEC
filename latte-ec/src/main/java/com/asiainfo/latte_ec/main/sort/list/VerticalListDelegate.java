package com.asiainfo.latte_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.R;

/**
 * 分类的垂直列表
 */

public class VerticalListDelegate extends LatteDelegate {
    public static VerticalListDelegate newInstance() {
        return new VerticalListDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.vertical_list_container;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
