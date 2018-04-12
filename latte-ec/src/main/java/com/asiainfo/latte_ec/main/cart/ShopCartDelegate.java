package com.asiainfo.latte_ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.delegates.bottom.BottomItemDelegate;
import com.asiainfo.latte_core.net.callback.ISuccess;
import com.asiainfo.latte_core.net.rt.RestClient;
import com.asiainfo.latte_core.ui.recycler.MultipleItemEntity;
import com.asiainfo.latte_core.util.log.LatteLogger;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;
import com.asiainfo.latte_ec.pay.FastPay;
import com.asiainfo.latte_ec.pay.IAlPayResultListener;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 购物车
 */
@SuppressWarnings("FieldCanBeLocal")
public class ShopCartDelegate extends BottomItemDelegate implements ICartItemListener, IAlPayResultListener {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectedAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStub mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;
    @BindView(R2.id.tool_bar)
    Toolbar toolbar;
    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    //购物车总数量
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0) {
            //没有选中的时候
            mIconSelectedAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyDataSetChanged();
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectedAll.setTextColor(Color.GRAY);
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();

        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getFiled(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }

        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getFiled(ShopCartItemFields.POSITION);

            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }

        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        final String orderUrl = "你的生成订单的Api";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数


        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        LatteLogger.e("ORDER", response);
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay
                                .create(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .setIAlPayResultListener(ShopCartDelegate.this)
                                .beginPayDialog();

                    }
                }).build()
                .post();

    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();

        if (count == 0 && mStubNoItem.getParent() != null) {
            final View studView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy = studView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    private void initData() {
        JSONObject response = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject dataOne = new JSONObject();
        dataOne.put("title", " Apple iPhone X");
        dataOne.put("desc", "64GB 深空灰色 移动联通电信4G手机");
        dataOne.put("id", 1);
        dataOne.put("price", 8388.00);
        dataOne.put("count", 2);
        dataOne.put("thumb", "https://img14.360buyimg.com/n0/jfs/t10675/253/1344769770/66891/92d54ca4/59df2e7fN86c99a27.jpg");
        array.add(dataOne);

        JSONObject dataTwo = new JSONObject();

        dataTwo.put("title", "三星 Galaxy S8");
        dataTwo.put("desc", "4GB+64GB 谜夜黑 移动联通电信4G手机 双卡双待");
        dataTwo.put("id", 2);
        dataTwo.put("price", 4999.00);
        dataTwo.put("count", 3);
        dataTwo.put("thumb", "https://img14.360buyimg.com/n0/jfs/t4549/6/4532220459/272946/d0b72af9/59119ddbNd25bdd22.jpg");
        array.add(dataTwo);
        response.put("data", array);

        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response.toString())
                        .converter();

        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));

        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        // 单个Item的总价预留 itemTotalPrice
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }


    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}

