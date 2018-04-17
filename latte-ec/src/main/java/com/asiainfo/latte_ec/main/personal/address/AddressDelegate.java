package com.asiainfo.latte_ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_core.net.callback.ISuccess;
import com.asiainfo.latte_core.net.rt.RestClient;
import com.asiainfo.latte_core.ui.recycler.MultipleItemEntity;
import com.asiainfo.latte_core.util.log.LatteLogger;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

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
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        LatteLogger.d("AddressDelegate", response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).converter();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);

    }
}
