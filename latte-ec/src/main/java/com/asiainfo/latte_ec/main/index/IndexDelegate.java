package com.asiainfo.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.asiainfo.latte_core.delegates.bottom.BottomItemDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ec.main.EcBottomDelegate;
import com.asiainfo.latte_ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.BindView;


/**
 * 主页
 */

public class IndexDelegate extends BottomItemDelegate {


    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
        mRefreshHandler.firstPage("index.php");
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_dark,
                android.R.color.holo_green_dark);

        mRefreshLayout.setProgressViewOffset(true, 120, 250);
    }
}
