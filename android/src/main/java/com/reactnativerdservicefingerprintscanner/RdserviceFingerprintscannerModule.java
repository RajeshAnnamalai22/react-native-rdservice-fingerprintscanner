package com.reactnativerdservicefingerprintscanner;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RdserviceFingerprintscannerModule.NAME)
public class RdserviceFingerprintscannerModule extends ReactContextBaseJavaModule implements RDServiceEvents {
    public static final String NAME = "RdserviceFingerprintscanner";

  private RDServiceManager rdServiceManager;
  String servicePackage = "";

    public RdserviceFingerprintscannerModule(ReactApplicationContext reactContext) {
        super(reactContext);
      reactContext.addActivityEventListener(new RDServiceActivityEventListener());
      rdServiceManager = new RDServiceManager.Builder(this).create();
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


  @ReactMethod
  public void getDeviceInfo( Promise promise) {
    final Activity activity = getCurrentActivity();
    rdServiceManager.discoverRdService(activity);
  }

  @ReactMethod
  public void captureFinger( Promise promise) {
    final Activity activity = getCurrentActivity();
    String PIDOptions =  "<PidOptions><Opts fCount=\"1\" fType=\"0\" iCount=\"0\" pCount=\"0\" format=\"0\" pidVer=\"2.0\" timeout=\"20000\" otp=\"\" posh=\"LEFT_INDEX\" env=\"S\" wadh=\"\" /> <Demo></Demo> <CustOpts> <Param name=\"Param1\" value=\"\" /> </CustOpts> </PidOptions>";
    rdServiceManager.captureRdService(servicePackage,PIDOptions,activity);

  }

  @Override
  public void onRDServiceDriverDiscovery(String rdServiceInfo, String rdServicePackage, Boolean isWhitelisted) {
    Toast.makeText(getCurrentActivity(), rdServiceInfo, Toast.LENGTH_SHORT).show();
    servicePackage = rdServicePackage;
  }

  @Override
  public void onRDServiceCaptureResponse(String pidData, String rdServicePackage) {
    Toast.makeText(getCurrentActivity(), pidData, Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onRDServiceDriverNotFound() {
    Toast.makeText(getCurrentActivity(), "DriverNotFound", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onRDServiceDriverDiscoveryFailed(int resultCode, Intent data, String rdServicePackage, String reason) {
    Toast.makeText(getCurrentActivity(), "DriverDiscoveryFailed", Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onRDServiceCaptureFailed(int resultCode, Intent data, String rdServicePackage) {
    Toast.makeText(getCurrentActivity(), "CaptureFailed", Toast.LENGTH_SHORT).show();
  }

  private class RDServiceActivityEventListener extends BaseActivityEventListener {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      super.onActivityResult(activity, requestCode, resultCode, data);
      Toast.makeText(getCurrentActivity(), "RESULT ACTIVITY", Toast.LENGTH_SHORT).show();
      rdServiceManager.onActivityResult(requestCode, resultCode, data);
    }
  }
}
