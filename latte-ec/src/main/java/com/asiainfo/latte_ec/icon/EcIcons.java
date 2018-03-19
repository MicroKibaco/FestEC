package com.asiainfo.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 字体图标集成与封装
 */

public enum EcIcons implements Icon {
    icon_scan('\ue616'),
    icon_ali_pay('\ue67c');
    private char character;


    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
