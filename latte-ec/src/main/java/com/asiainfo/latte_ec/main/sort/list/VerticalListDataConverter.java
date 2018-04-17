package com.asiainfo.latte_ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_ui.recycler.DataConverter;
import com.asiainfo.latte_ui.recycler.ItemType;
import com.asiainfo.latte_ui.recycler.MultipleFields;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 垂直列表数据
 */

public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> converter() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, false)
                    .build();

            dataList.add(entity);
            dataList.get(0).setFields(MultipleFields.TAG, true);
        }

        return dataList;
    }
}
