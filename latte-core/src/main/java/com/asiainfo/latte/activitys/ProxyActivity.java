package com.asiainfo.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.asiainfo.latte.R;
import com.asiainfo.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public abstract class ProxyActivity extends SupportActivity {


    public abstract LatteDelegate setRootDelegate();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {


        final ContentFrameLayout container = new ContentFrameLayout(this);

        container.setId(R.id.delegate_container);

        setContentView(container);

        if (savedInstanceState == null) {

            loadRootFragment(R.id.delegate_container, setRootDelegate());

        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
