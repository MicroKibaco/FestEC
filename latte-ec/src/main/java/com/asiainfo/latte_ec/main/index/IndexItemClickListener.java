package com.asiainfo.latte_ec.main.index;

import android.view.View;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.detail.GoodsDetailDelegate;
import com.asiainfo.latte_ui.recycler.MultipleFields;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;


class IndexItemClickListener extends SimpleClickListener {


    private final LatteDelegate DELEGATE;


    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static IndexItemClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final int goodsId = entity.getField(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
