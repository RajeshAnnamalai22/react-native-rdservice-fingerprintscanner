package com.reactnativerdservicefingerprintscanner;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RdserviceFingerprintscannerModule.NAME)
public class RdserviceFingerprintscannerModule extends ReactContextBaseJavaModule implements RDServiceEvents {
    public static final String NAME = "RdserviceFingerprintscanner";
    public Promise promise;

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
    this.promise = promise;

  }

  @ReactMethod
  public void captureFinger( Promise promise) {
    final Activity activity = getCurrentActivity();
    String PIDOptions =  "<PidOptions><Opts fCount=\"1\" fType=\"0\" iCount=\"0\" pCount=\"0\" format=\"0\" pidVer=\"2.0\" timeout=\"20000\" otp=\"\" posh=\"LEFT_INDEX\" env=\"S\" wadh=\"\" /> <Demo></Demo> <CustOpts> <Param name=\"Param1\" value=\"\" /> </CustOpts> </PidOptions>";
    rdServiceManager.captureRdService(servicePackage,PIDOptions,activity);
    this.promise = promise;

  }

  @Override
  public void onRDServiceDriverDiscovery(String rdServiceInfo, String rdServicePackage, Boolean isWhitelisted) {
    // Called when an installed driver is discovered
    servicePackage = rdServicePackage;

    WritableMap responseData = Arguments.createMap();
    responseData.putInt("status",1);
    responseData.putString("rdServiceInfo", rdServiceInfo);
    responseData.putString("rdServicePackage", rdServicePackage);
    responseData.putBoolean("isWhitelisted",isWhitelisted);
    promise.resolve(responseData);
  }

  @Override
  public void onRDServiceCaptureResponse(String pidData, String rdServicePackage) {
    // Called when fingerprint is successfully captured
    WritableMap responseData = Arguments.createMap();
    responseData.putInt("status",1);
    responseData.putString("pidData", pidData);
    responseData.putString("rdServicePackage", rdServicePackage);
    promise.resolve(responseData);

  }

  @Override
  public void onRDServiceDriverNotFound() {
    // Called when no installed driver is found
    Toast.makeText(getCurrentActivity(), "DriverNotFound", Toast.LENGTH_SHORT).show();
    WritableMap responseData = Arguments.createMap();
    responseData.putInt("status",0);
    responseData.putString("message","Driver Not Found");
    promise.resolve(responseData);
  }

  @Override
  public void onRDServiceDriverDiscoveryFailed(int resultCode, Intent data, String rdServicePackage, String reason) {
    // Called when a discovered driver fails to provide a proper status information
    Toast.makeText(getCurrentActivity(), "DriverDiscoveryFailed", Toast.LENGTH_SHORT).show();
    promise.reject("DRIVER_DISCOVERY_FAILED","Driver Discovery Failed");

  }

  @Override
  public void onRDServiceCaptureFailed(int resultCode, Intent data, String rdServicePackage) {
    // Called when fingerprint capture fails
    Toast.makeText(getCurrentActivity(), "CaptureFailed", Toast.LENGTH_SHORT).show();
    promise.reject("FINGERPRINT_CAPTURE__FAILED","FingerPrint Capture Failed");
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
