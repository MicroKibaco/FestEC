package com.asiainfo.latte_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.asiainfo.latte_core.delegates.bottom.BottomItemDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.main.sort.content.ContentDelegate;
import com.asiainfo.latte_ec.main.sort.list.VerticalListDelegate;


/**
 * 分类
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,
                VerticalListDelegate.newInstance());

        // 设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,
                ContentDelegate.newInstance(1));

    }
}
