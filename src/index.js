import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-rdservice-fingerprintscanner' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const RdserviceFingerprintscanner = NativeModules.RdserviceFingerprintscanner
  ? NativeModules.RdserviceFingerprintscanner
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );
export const DEFAULT_PID_OPTIONS = `<PidOptions ver="1.0"> <Opts fCount="1" fType="0" iCount="0" pCount="0" format="0" pidVer="2.0" timeout="20000" otp="" posh="UNKNOWN" env="P" wadh="" /> <Demo></Demo><CustOpts> <Param name="ValidationKey" value="" /> </CustOpts> </PidOptions>`;

export function getDeviceInfo() {
  return new Promise((resolve, reject) => {
    RdserviceFingerprintscanner.getDeviceInfo()
      .then((res) => {
        if (res.status === -1) {
          const resObj = {
            status: res.status,
            message: res.message,
          };
          resolve(resObj);
        } else {
          const resObj = {
            isWhitelisted: res.isWhitelisted,
            rdServiceInfoJson: JSON.parse(res.rdServiceInfoJsonString),
            rdServiceInfoXML: res.rdServiceInfoXML,
            rdServicePackage: res.rdServicePackage,
            status: res.status,
            message: res.message,
          };
          resolve(resObj);
        }
      })
      .catch((err) => {
        reject(err);
      });
  });
}

export function captureFinger(pidOptions = DEFAULT_PID_OPTIONS) {
  return new Promise((resolve, reject) => {
    RdserviceFingerprintscanner.captureFinger(pidOptions)
      .then((res) => {
        const resObj = {
          pidDataJson: JSON.parse(res.pidDataJsonString),
          pidDataXML: res.pidDataXML,
          rdServicePackage: res.rdServicePackage,
          status: res.status,
          errInfo: res.errInfo,
          errorCode: parseInt(res.errorCode),
          message: res.message,
        };
        resolve(resObj);
      })
      .catch((err) => {
        reject(err);
      });
  });
}
