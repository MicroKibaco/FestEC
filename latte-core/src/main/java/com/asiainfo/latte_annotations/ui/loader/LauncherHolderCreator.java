package com.asiainfo.latte_annotations.ui.loader;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by MicroKibaco on 11/03/2018.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
