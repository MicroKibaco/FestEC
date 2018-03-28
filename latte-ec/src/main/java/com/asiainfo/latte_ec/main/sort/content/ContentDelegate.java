package com.asiainfo.latte_ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.latte_core.delegates.LatteDelegate;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * 内容区
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    @BindView(R2.id.rv_content_list)
    RecyclerView mRecyclerView = null;
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.content_list_container;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {

        //实现假数据

        JSONObject data = new JSONObject();
        JSONArray dataArray = new JSONArray();

        //第一条数据

        JSONObject contentData1 = new JSONObject();
        contentData1.put("id", 1);
        contentData1.put("section", "手机数码");
        JSONArray goodsArray1 = new JSONArray();
        JSONObject goodData1 = new JSONObject();
        goodData1.put("goods_id", "33");
        goodData1.put("goods_name", "三星 Galaxy S8");
        goodData1.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t5050/24/2423856155/276827/875c97bd/5900053cN3ff7ee20.jpg");

        JSONObject goodData2 = new JSONObject();
        goodData2.put("goods_id", "44");
        goodData2.put("goods_name", "Apple iPhone 6s Plus");
        goodData2.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t2302/16/135479564/94882/c76da045/55f0e877N3c24faa3.jpg");

        JSONObject goodData3 = new JSONObject();
        goodData3.put("goods_id", "55");
        goodData3.put("goods_name", "Apple iPhone X");
        goodData3.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t10675/253/1344769770/66891/92d54ca4/59df2e7fN86c99a27.jpg");

        JSONObject goodData4 = new JSONObject();
        goodData4.put("goods_id", "66");
        goodData4.put("goods_name", "Apple iPhone 8 Plus");
        goodData4.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg");

        goodsArray1.add(goodData1);
        goodsArray1.add(goodData2);
        goodsArray1.add(goodData3);
        goodsArray1.add(goodData4);
        contentData1.put("goods", goodsArray1);

        dataArray.add(contentData1);

        //第二条数据

        JSONObject contentData2 = new JSONObject();
        contentData2.put("id", 2);
        contentData2.put("section", "笔记本电脑");
        JSONArray goodsArray2 = new JSONArray();
        JSONObject goodData21 = new JSONObject();
        goodData21.put("goods_id", "333");
        goodData21.put("goods_name", "Apple MacBook Air");
        goodData21.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t5935/195/2108753717/176060/c849dcb6/593a49a3Nf9c2a052.jpg");

        JSONObject goodData22 = new JSONObject();
        goodData22.put("goods_id", "444");
        goodData22.put("goods_name", "联想(Lenovo)拯救者R720");
        goodData22.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t11746/357/1277342409/188824/39be5a8d/59ffd46eN750fc606.jpg");

        JSONObject goodData32 = new JSONObject();
        goodData32.put("goods_id", "555");
        goodData32.put("goods_name", "戴尔DELL灵越游匣");
        goodData32.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t12994/338/733574425/319777/f266f597/5a128bebN55293392.jpg");

        JSONObject goodData42 = new JSONObject();
        goodData42.put("goods_id", "666");
        goodData42.put("goods_name", "机械革命 MECHREVO");
        goodData42.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t14068/348/432242762/292807/696dde8a/5a2604aeN14d74743.jpg");

        JSONObject goodData52 = new JSONObject();
        goodData52.put("goods_id", "777");
        goodData52.put("goods_name", "小米(MI)Air ");
        goodData52.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t6700/155/2098998076/156185/6cf95035/595dd5a5Nc3a7dab5.jpg");


        goodsArray2.add(goodData21);
        goodsArray2.add(goodData22);
        goodsArray2.add(goodData32);
        goodsArray2.add(goodData42);
        goodsArray2.add(goodData52);
        contentData2.put("goods", goodsArray2);

        dataArray.add(contentData2);

        //第三条数据

        JSONObject contentData3 = new JSONObject();
        contentData3.put("id", 3);
        contentData3.put("section", "摄影摄像");
        JSONArray goodsArray3 = new JSONArray();
        JSONObject goodData31 = new JSONObject();
        goodData31.put("goods_id", "3333");
        goodData31.put("goods_name", "富士（FUJIFILM）X-A3 XC16-50mm");
        goodData31.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t3274/128/2067051839/311129/7bb75e4d/57d91353N70e869fa.jpg");

        JSONObject goodData332 = new JSONObject();
        goodData332.put("goods_id", "4444");
        goodData332.put("goods_name", "尼康（Nikon）J5+1 ");
        goodData332.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t11746/357/1277342409/188824/39be5a8d/59ffd46eN750fc606.jpg");

        JSONObject goodData33 = new JSONObject();
        goodData33.put("goods_id", "5555");
        goodData33.put("goods_name", "松下数码相机（Panasonic）Lumix");
        goodData33.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t15310/170/758491604/95842/38c28b1e/5a3792a8Nb5be20aa.jpg");

        JSONObject goodData34 = new JSONObject();
        goodData34.put("goods_id", "6666");
        goodData34.put("goods_name", "奥林巴斯（OLYMPUS）");
        goodData34.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t8089/154/1416091205/283614/9a220654/59b8f7d4Nd7ad951a.jpg");

        JSONObject goodData35 = new JSONObject();
        goodData35.put("goods_id", "7777");
        goodData35.put("goods_name", "佳能（Canon）EOS");
        goodData35.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t2656/295/34058120/362134/d92995e5/56fc835dNe349b797.jpg");

        JSONObject goodData36 = new JSONObject();
        goodData36.put("goods_id", "8888");
        goodData36.put("goods_name", "索尼（SONY）");
        goodData36.put("goods_thumb", "https://img14.360buyimg.com/n0/jfs/t7375/352/3570773479/158608/3e16e9a0/59f30ad2Nad629b38.jpg");


        goodsArray3.add(goodData31);
        goodsArray3.add(goodData332);
        goodsArray3.add(goodData33);
        goodsArray3.add(goodData34);
        goodsArray3.add(goodData35);
        goodsArray3.add(goodData36);
        contentData3.put("goods", goodsArray3);

        dataArray.add(contentData3);

        data.put("data", dataArray);


        mData = new SectionDataConverter().converter(data.toString());
        final SectionAdapter sectionAdapter =
                new SectionAdapter(R.layout.item_section_content,
                        R.layout.item_section_header, mData);
        mRecyclerView.setAdapter(sectionAdapter);
    }
}
