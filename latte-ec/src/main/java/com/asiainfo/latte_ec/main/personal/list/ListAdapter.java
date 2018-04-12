package com.asiainfo.latte_ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_ec.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:

                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_value, item.getValue());

                break;

            case ListItemType.ITEM_AVATAR:

                Glide
                        .with(mContext)
                        .load(item.getImageUrl())
                        .apply(Latte.getRecyclerOptions())
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));

                break;

            case ListItemType.ITEM_SWITCH:

                helper.setText(R.id.tv_arrow_switch_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());

                break;

            default:
                break;
        }
    }
}
