package com.asiainfo.latte_ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.ui.recycler.MultipleFields;
import com.asiainfo.latte_core.ui.recycler.MultipleItemEntity;
import com.asiainfo.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.asiainfo.latte_core.ui.recycler.MultipleViewHolder;
import com.asiainfo.latte_ec.R;
import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;


public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;

    ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getFiled(ShopCartItemFields.PRICE);
            final int count = entity.getFiled(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //先取出所有值
                final int id = entity.getFiled(MultipleFields.ID);
                final String thumb = entity.getFiled(MultipleFields.IMAGE_URL);
                final String title = entity.getFiled(ShopCartItemFields.TITLE);
                final String desc = entity.getFiled(ShopCartItemFields.DESC);
                final int count = entity.getFiled(ShopCartItemFields.COUNT);
                final double price = entity.getFiled(ShopCartItemFields.PRICE);

                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide
                        .with(mContext)
                        .load(thumb)
                        .apply(Latte.getRecyclerOptions())
                        .into(imgThumb);

                //在左侧勾选渲染之前改变全选状态
                entity.setFields(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getFiled(ShopCartItemFields.IS_SELECTED);

                //根据数据状态左侧勾勾点击事件
                if (isSelected) {
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));

                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //每次点击取值 是动态赋值到entity里,所以点击时需要额外取一下
                        final boolean currentSelected = entity.getFiled(ShopCartItemFields.IS_SELECTED);

                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setFields(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setFields(ShopCartItemFields.IS_SELECTED, true);

                        }
                    }
                });
                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getFiled(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            //数量传给服务器 再实现本地加减逻辑
                            int countNum = Integer.parseInt(tvCount.getText().toString());
                            countNum--;
                            tvCount.setText(String.valueOf(countNum));
                            if (mCartItemListener != null) {
                                mTotalPrice = mTotalPrice - price;
                                final double itemTotal = countNum * price;
                                mCartItemListener.onItemClick(itemTotal);
                            }
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentCount = entity.getFiled(ShopCartItemFields.COUNT);
                        //数量传给服务器 再实现本地加减逻辑
                        int countNum = Integer.parseInt(tvCount.getText().toString());
                        countNum++;
                        tvCount.setText(String.valueOf(countNum));
                        if (mCartItemListener != null) {
                            mTotalPrice = mTotalPrice + price;
                            final double itemTotal = countNum * price;
                            mCartItemListener.onItemClick(itemTotal);
                        }
                    }
                });
                break;
            default:
                break;
        }

    }
}

