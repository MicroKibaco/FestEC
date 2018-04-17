package com.asiainfo.latte_ec.main.personal.order;

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
import com.asiainfo.latte_ec.main.personal.PersonalDelegate;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

public class OrderListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;
    private String mType = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //请求数据
        JSONObject params = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject data1 = new JSONObject();
        data1.put("thumb", 1);
        data1.put("title", true);
        data1.put("id", "2222");
        data1.put("price", "4444444444444");
        data1.put("time", "灌灌灌灌");
        array.add(data1);

        JSONObject data2 = new JSONObject();
        data2.put("thumb", 2);
        data2.put("title", false);
        data2.put("id", "1111");
        data2.put("price", "13822222222");
        data2.put("time", "哈哈哈");
        array.add(data2);
        params.put("data", array);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new OrderListDataConverter().setJsonData(params.toString()).converter();
        final OrderListAdapter adapter = new OrderListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }


}
