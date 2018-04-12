package com.asiainfo.latte_core.delegates;

/**
 * Created by MicroKibaco on 03/03/2018.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
