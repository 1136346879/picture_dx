package com.xfs.qrcode_module.manager;

import android.hardware.Camera;
import android.util.Log;

import java.util.List;

/**
 * @author yangyi  2018年08月05日01:27:47
 *         <p>
 *         wechat: yangyi_451686712
 */
public class LightManager {

    private static final String TAG = "y-zxing";
    private static LightManager lightManager;

    public static synchronized LightManager getInstance() {
        if (lightManager == null) {
            lightManager = new LightManager();
        }
        return lightManager;
    }

    private LightManager() {
    }

    /**
     * 开灯
     */
    public void turnLightOn(Camera camera) {
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            return;
        }

        List<String> flashModes = parameters.getSupportedFlashModes();
        if (flashModes == null) {
            return;
        }
        String flashMode = parameters.getFlashMode();
        Log.i(TAG, "Flash mode: " + flashMode);
        Log.i(TAG, "Flash modes: " + flashModes);
        // 闪光灯关闭状态
        if (!Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
            // Turn on the flash
            if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            } else {
            }
        }
    }

    /**
     * 关灯
     */
    public void turnLightOff(Camera camera) {
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        String flashMode = parameters.getFlashMode();
        // Check if camera flash exists
        if (flashModes == null) {
            return;
        }
        // 闪光灯打开状态
        if (!Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
            // Turn off the flash
            if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
            } else {
                Log.e(TAG, "FLASH_MODE_OFF not supported");
            }
        }
    }
}
