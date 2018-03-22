package com.asiainfo.latte_ec.main;

import android.graphics.Color;

import com.asiainfo.latte_core.delegates.bottom.BaseBottomDelegate;
import com.asiainfo.latte_core.delegates.bottom.BottomItemDelegate;
import com.asiainfo.latte_core.delegates.bottom.BottomTabBean;
import com.asiainfo.latte_core.delegates.bottom.ItemBuilder;
import com.asiainfo.latte_ec.R;
import com.asiainfo.latte_ec.main.cart.ShopCartDelegate;
import com.asiainfo.latte_ec.main.discover.DiscoverDelegate;
import com.asiainfo.latte_ec.main.index.IndexDelegate;
import com.asiainfo.latte_ec.main.personal.PersonalDelegate;
import com.asiainfo.latte_ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        final String[] titles = getResources().getStringArray(R.array.bottom_nav_bar_title);
        final String[] icons = getResources().getStringArray(R.array.bottom_nav_bar_icon);
        items.put(new BottomTabBean(icons[0], titles[0]), new IndexDelegate());
        items.put(new BottomTabBean(icons[1], titles[1]), new SortDelegate());
        items.put(new BottomTabBean(icons[2], titles[2]), new DiscoverDelegate());
        items.put(new BottomTabBean(icons[3], titles[3]), new ShopCartDelegate());
        items.put(new BottomTabBean(icons[4], titles[4]), new PersonalDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
