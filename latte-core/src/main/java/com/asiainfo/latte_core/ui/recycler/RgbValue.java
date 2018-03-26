package com.asiainfo.latte_core.ui.recycler;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RgbValue {
    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }

    public abstract int red();

    public abstract int green();

    public abstract int blue();
}
