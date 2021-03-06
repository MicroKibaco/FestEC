package com.asiainfo.latte_ec.main.personal.order;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ui.recycler.MultipleFields;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;
import com.asiainfo.latte_ui.recycler.MultipleRecyclerAdapter;
import com.asiainfo.latte_ui.recycler.MultipleViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

public class OrderListAdapter extends MultipleRecyclerAdapter {

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleVal = item.getField(MultipleFields.TITLE);
                final String timeVal = item.getField(OrderItemFields.TIME);
                final double priceVal = item.getField(OrderItemFields.PRICE);
                final String imageUrl = item.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(Latte.getRecyclerOptions())
                        .into(imageView);

                title.setText(titleVal);
                price.setText("价格：" + String.valueOf(priceVal));
                time.setText("时间：" + timeVal);
                break;
            default:
                break;
        }
    }
}
