package com.asiainfo.latte_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ec.main.sort.SortDelegate;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * 分类的垂直列表
 */

public class VerticalListDelegate extends LatteDelegate {
    private static final int SORT_COUNT = 20;
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    public static VerticalListDelegate newInstance() {
        return new VerticalListDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.vertical_list_container;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecycleView();
    }

    private void initRecycleView() {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        // 屏蔽动画
        mRecyclerView.setAnimation(null);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        JSONObject data = new JSONObject();
        JSONObject list = new JSONObject();
        JSONArray array = new JSONArray();
        data.put("data", list);
        list.put("list", array);
        for (int i = 0; i < SORT_COUNT; i++) {
            JSONObject json = new JSONObject();
            json.put("id", i);
            json.put("name", "分类" + i);
            array.add(json);
        }

        final List<MultipleItemEntity> dataBean =
                new VerticalListDataConverter().setJsonData(data.toString()).converter();

        final SortDelegate delegate = getParentDelegate();
        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(dataBean, delegate);
        mRecyclerView.setAdapter(adapter);

    }
}
