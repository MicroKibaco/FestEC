package com.asiainfo.latte_ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_core.net.callback.ISuccess;
import com.asiainfo.latte_core.util.log.LatteLogger;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;


public class AddressDelegate extends LatteDelegate implements ISuccess {


    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //请求数据
        JSONObject params = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject data1 = new JSONObject();
        data1.put("id", 1);
        data1.put("default", true);
        data1.put("name", "隔壁老陈");
        data1.put("phone", "1381111111");
        data1.put("address", "宝翠商务");
        array.add(data1);

        JSONObject data2 = new JSONObject();
        data2.put("id", 2);
        data2.put("default", false);
        data2.put("name", "厕所一日游");
        data2.put("phone", "13822222222");
        data2.put("address", "东胜广场");
        array.add(data2);
        params.put("data", array);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(params.toString()).converter();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }

    @Override
    public void onSuccess(String response) {
        LatteLogger.d("AddressDelegate", response);
    }
}
