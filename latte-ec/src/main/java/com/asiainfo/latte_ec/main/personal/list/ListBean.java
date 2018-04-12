package com.asiainfo.latte_ec.main.personal.list;

import android.widget.CompoundButton;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;


    public ListBean(int itemType, String imageUrl,
                    String text, String value,
                    int id, LatteDelegate delegate,
                    CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mItemType = itemType;
        this.mImageUrl = imageUrl;
        this.mText = text;
        this.mValue = value;
        this.mId = id;
        this.mDelegate = delegate;
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private LatteDelegate delegate = null;

        public int getId() {
            return id;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public int getItemType() {
            return itemType;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public String getText() {
            return text;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
            return onCheckedChangeListener;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public LatteDelegate getDelegate() {
            return delegate;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {

            return new ListBean(itemType, imageUrl,
                    text, value,
                    id, delegate,
                    onCheckedChangeListener);
        }
    }
}
