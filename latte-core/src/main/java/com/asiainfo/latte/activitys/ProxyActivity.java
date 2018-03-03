package com.asiainfo.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.asiainfo.latte.R;
import com.asiainfo.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 基础Activity
 */

public abstract class ProxyActivity extends SupportActivity {

    /**
     * Fragment对象
     */
    public abstract LatteDelegate setRootDelegate();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    /**
     * 布局容器
     * @param savedInstanceState
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {


        final ContentFrameLayout container = new ContentFrameLayout(this);

        container.setId(R.id.delegate_container);

        setContentView(container);

        if (savedInstanceState == null) {

            loadRootFragment(R.id.delegate_container, setRootDelegate());

        }


    }

    /**
     * 垃圾回收,降低资源在页面销毁后的销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
