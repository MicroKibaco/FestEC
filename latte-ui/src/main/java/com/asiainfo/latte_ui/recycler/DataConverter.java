package com.asiainfo.latte_ui.recycler;

import java.util.ArrayList;

/**
 * 数据转换的约束
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJson = null;

    public abstract ArrayList<MultipleItemEntity> converter();

    public void cleanData() {
        ENTITIES.clear();
    }

    protected String getJsonData() {

        if (mJson == null || mJson.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!!!");
        }
        return mJson;
    }

    public DataConverter setJsonData(String jsonData) {
        this.mJson = jsonData;
        return this;
    }


}
