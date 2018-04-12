package com.asiainfo.latte_core.delegates;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.asiainfo.latte_annotations.R;
import com.asiainfo.latte_core.ui.callback.CallbackManager;
import com.asiainfo.latte_core.ui.callback.CallbackType;
import com.asiainfo.latte_core.ui.callback.IGlobalCallback;
import com.asiainfo.latte_core.ui.camera.CameraImageBean;
import com.asiainfo.latte_core.ui.camera.LatteCamera;
import com.asiainfo.latte_core.ui.camera.RequestCodes;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@SuppressWarnings("unchecked")
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    // 不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        LatteCamera.start(this);
    }

    // 这个是真正调用的方法
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        tip(getString(R.string.tip_denied_camera));
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        tip(getString(R.string.tip_never_camera));
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton(getString(R.string.btn_agree_use), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int what) {
                        request.proceed();
                    }
                })
                .setNegativeButton(getString(R.string.btn_disagree_use), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int what) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(getString(R.string.msg_permission_manager))
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400)
                            .start(getContext(), this);
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        // 从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        assert pickPath != null;
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);

                    }
                    break;
                case RequestCodes.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到裁剪后的数据进行处理
                    final IGlobalCallback callback =
                            CallbackManager
                                    .getInstance()
                                    .getCallback(CallbackType.ON_CROP);
                    if (callback != null) {
                        callback.executeCallBack(cropUri);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    tip(getString(R.string.tip_crop_error));
                    break;
                default:
                    break;
            }
        }
    }
}
