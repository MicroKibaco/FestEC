package com.asiainfo.latte_annotations.delegates;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public abstract class LatteDelegate extends PermissionDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
