package com.asiainfo.latte_ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_core.util.callback.CallbackManager;
import com.asiainfo.latte_core.util.callback.CallbackType;
import com.asiainfo.latte_core.util.callback.IGlobalCallback;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ui.widget.AutoPhotoLayout;
import com.asiainfo.latte_ui.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;



public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        tip(getString(R.string.tip_comment) + mStarLayout.getStarCount());
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallBack(@Nullable Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
