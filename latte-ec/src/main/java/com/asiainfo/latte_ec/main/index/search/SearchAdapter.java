package com.asiainfo.latte_ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ui.recycler.MultipleFields;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;
import com.asiainfo.latte_ui.recycler.MultipleRecyclerAdapter;
import com.asiainfo.latte_ui.recycler.MultipleViewHolder;

import java.util.List;


public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (item.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = item.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
