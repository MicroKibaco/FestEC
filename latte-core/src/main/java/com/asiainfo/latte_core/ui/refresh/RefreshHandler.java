package com.asiainfo.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.app.Latte;
import com.asiainfo.latte_core.net.callback.ISuccess;
import com.asiainfo.latte_core.net.rt.RestClient;
import com.asiainfo.latte_core.ui.recycler.DataConverter;
import com.asiainfo.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 下拉刷新处理器
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final RecyclerView RECYCLERVIEW;
    private final DataConverter CONVERTER;
    private final PagingBean BEAN;
    private MultipleRecyclerAdapter mAdapter = null;

    public RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerview,
                          DataConverter dataConverter,
                          PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = dataConverter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 3000);
    }

    /**
     * 伪报文
     */
    public void firstPage(String url) {
        // 首页假数据
        JSONObject data = new JSONObject();
        JSONArray dataArray = new JSONArray();
        data.put("total", 100);
        data.put("page_size", 6);
        JSONObject goodsOne = new JSONObject();
        goodsOne.put("goodsId", 0);
        goodsOne.put("spanSize", 4);
        JSONArray bannersArray = new JSONArray();
        bannersArray.add("https://img20.360buyimg.com/da/jfs/t15508/278/1278820544/140883/249bd12b/5a4dd00dN8e4345f8.jpg");
        bannersArray.add("https://img12.360buyimg.com/da/jfs/t5182/69/462910155/142359/66551a05/58ffffd8N892ce4a8.jpg");
        bannersArray.add("https://img12.360buyimg.com/da/jfs/t12040/365/2282518742/197444/b45a2120/5a38e356Nfb55a3b3.jpg");
        bannersArray.add("https://img12.360buyimg.com/babel/jfs/t13651/286/2569410178/99021/185da492/5a449d59Nd9781983.jpg");
        bannersArray.add("https://img11.360buyimg.com/da/jfs/t14419/276/543521012/97503/a0c34b9/5a310106Nbc61ad65.jpg");
        goodsOne.put("banners", bannersArray);
        dataArray.add(goodsOne);
        // 第二条数据
        JSONObject goodsTwo = new JSONObject();
        goodsTwo.put("goodsId", 1);
        goodsTwo.put("spanSize", 4);
        goodsTwo.put("text", "卡门KM男装2017冬季新品羽绒服");
        goodsTwo.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t10639/31/2566861557/291861/5b71f7ab/59faab2dNe787f98b.jpg");
        dataArray.add(goodsTwo);
        // 第三条数据
        JSONObject goodsThree = new JSONObject();
        goodsThree.put("goodsId", 2);
        goodsThree.put("spanSize", 4);
        goodsThree.put("text", "NIKE 耐克Air Jordan1 Mid");
        goodsThree.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t6970/257/2440522244/114982/5ba8d/598bd6dbNb9455bfd.jpg");
        dataArray.add(goodsThree);
        // 第四条数据
        JSONObject goodsFour = new JSONObject();
        goodsFour.put("goodsId", 3);
        goodsFour.put("spanSize", 4);
        goodsFour.put("text", "adidas 阿迪达斯 三叶草");
        goodsFour.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t13894/323/2369736625/41580/e3eddc8d/5a3b62c1N107d3523.jpg");
        dataArray.add(goodsFour);
        // 第5条数据
        JSONObject goodsFive = new JSONObject();
        goodsFive.put("goodsId", 4);
        goodsFive.put("spanSize", 2);
        goodsFive.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t8641/351/111309782/305243/fcc13206/59a0e9fcN52450b6c.jpg");
        dataArray.add(goodsFive);
        // 第6条数据
        JSONObject goodsSix = new JSONObject();
        goodsSix.put("goodsId", 4);
        goodsSix.put("spanSize", 2);
        goodsSix.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t8641/351/111309782/305243/fcc13206/59a0e9fcN52450b6c.jpg");
        dataArray.add(goodsSix);
        // 第7条数据
        JSONObject goodsSeven = new JSONObject();
        goodsSeven.put("goodsId", 4);
        goodsSeven.put("spanSize", 2);
        goodsSeven.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t7984/37/1781418694/139255/5c6a8951/59a0ea2dNdd7e5d16.jpg");
        dataArray.add(goodsSeven);
        // 第8条数据
        JSONObject goodsEight = new JSONObject();
        goodsEight.put("goodsId", 4);
        goodsEight.put("spanSize", 2);
        goodsEight.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t7984/37/1781418694/139255/5c6a8951/59a0ea2dNdd7e5d16.jpg");
        dataArray.add(goodsEight);
        // 第9条数据
        JSONObject goodsNine = new JSONObject();
        goodsNine.put("goodsId", 5);
        goodsNine.put("spanSize", 4);
        goodsNine.put("text", "------> 享品质 <------");
        dataArray.add(goodsNine);
        // 第10条数据
        JSONObject goodsTen = new JSONObject();
        goodsTen.put("goodsId", 6);
        goodsTen.put("spanSize", 4);
        goodsTen.put("text", "李施德林（Listerine）漱口水");
        goodsTen.put("imageUrl", "https://img14.360buyimg.com/n0/jfs/t3274/258/2246320980/94882/2102bb8c/57de3529N45081a94.jpg");
        dataArray.add(goodsTen);
        data.put("data", dataArray);

        BEAN.setDelayed(2000);
        final JSONObject object = JSON.parseObject(data.toString());
        BEAN.setTotal(object.getInteger("total"))
                .setPageSize(object.getInteger("page_size"));

        // 设置adapter
        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(data.toString()));
        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
        RECYCLERVIEW.setAdapter(mAdapter);
        BEAN.addIndex();
    }


    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }

    private void paging(final String url) {

        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // TODO: 模拟网络请求
                    RestClient.builder().url(url + index).success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                            CONVERTER.cleanData();
                            mAdapter.addData(CONVERTER.setJsonData(response).converter());
                            // 累加数量
                            BEAN.setCurrentCount(mAdapter.getData().size());
                            mAdapter.loadMoreComplete();
                            BEAN.addIndex();
                        }
                    })
                            .build()
                            .get();

                }
            }, 1000);
        }


    }

}
