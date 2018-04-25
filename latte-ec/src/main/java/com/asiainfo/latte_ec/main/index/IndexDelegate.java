package com.asiainfo.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.asiainfo.latte_core.delegates.bottom.BottomItemDelegate;
import com.asiainfo.latte_core.util.callback.CallbackManager;
import com.asiainfo.latte_core.util.callback.CallbackType;
import com.asiainfo.latte_core.util.callback.IGlobalCallback;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ec.main.EcBottomDelegate;
import com.asiainfo.latte_ec.main.index.search.SearchDelegate;
import com.asiainfo.latte_ui.recycler.BaseDecoration;
import com.asiainfo.latte_ui.refresh.RefreshHandler;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 主页
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {


    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallBack(@Nullable String args) {
                        tip(getString(R.string.text_get_qr_code) + args);
                    }
                });
        mSearchView.setOnFocusChangeListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    private void initRecyclerView() {

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
        //  mRefreshHandler.firstPage("index.php");
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_dark,
                android.R.color.holo_green_dark);

        mRefreshLayout.setProgressViewOffset(true, 120, 250);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().getSupportDelegate().start(new SearchDelegate());
        }
    }

}
