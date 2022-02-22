# react-native-rdservice-fingerprintscanner

React Native library to easily integrate Fingerprint Device support in your app (for UIDAI Aadhaar based secure authentication in India). It is only for Android Devices.

As per [UIDAI](https://uidai.gov.in/) (Aadhaar) guidelines, only registered biometric devices can be used for Aadhaar Authentication. These devices come with RDService drivers (usually available on PlayStore) that exposes a standard API.

This library makes it easy to work with all such devices so that your app can search for installed drivers and get the fingerprint data after a scan.

For reference, you may check out the ([Aadhaar Registered Devices by UIDAI](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)).




## Installation

```sh
npm install react-native-rdservice-fingerprintscanner
```

Add jitpack in your root build.gradle file at the end of repositories: ```android/build.gradle```

```java
allprojects {
  repositories {
    // ...
    maven { url 'https://jitpack.io' }
  }
}
```

## Usage

```js
import { getDeviceInfo, captureFinger } from "react-native-rdservice-fingerprintscanner";

// ...

getDeviceInfo()
      .then((response) => {
        console.log(response, 'DEVICE DRIVER FOUND'); // Response about Device Driver
      })
      .catch((error) => {
        console.log(error, 'DEVICE DRIVER NOT FOUND'); //Failed to get device information
      });
      
    captureFinger(pidOptions)
      .then((response) => {
        console.log(response, 'FINGER CAPTURE'); // FingerPrint Response
      })
      .catch((e) => {
        console.log(e, 'ERROR_FINGER_CAPTURE'); // Failed to capture the Fingerprint
      });
```


```pidOptions``` is an XML String that you have to pass to ```captureFinger``` method. Refer [UIDAI Document](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)

```Note``` : Call ```captureFinger()``` Method after getting response from getDeviceInfo() method. Calling of ```captureFinger()``` method before ```getDeviceInfo()``` method, only returns Error in ```catch``` block. Refer [Example Code](https://github.com/senthalan2/react-native-rdservice-fingerprintscanner/blob/main/example/src/App.js) 


## Response JSON Object

```getDeviceInfo```

Key | Value | Description  
--- | --- | ---
status | -1 or 1 or 0 | ```-1``` - Device Driver not Found, ```1``` - READY, ```0``` - NOTREADY
isWhitelisted | true or false | iT is about the Device is Approved or not. ```true``` - Approved, ```false``` - Not Approved  
rdServiceInfoJson |JSON DATA | The device returns XML DATA of Device Information. this parameter contains converted JSON DATA of XML DATA
rdServiceInfoXML | XML DATA | Device Information
rdServicePackage | Device Package

```captureFinger```

Key | Value | Description  
--- | --- | ---
status | 1 or 0 | ```1``` - Fingerprint Captured Successfully, ```0``` - FingerPrint not Captured (Check Connection of Device and OTG Connection Settings in Mobile)
errorCode | ERROR CODE from RD Service | Refer [Error Code Document](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)
errInfo | Error Message according to the ERROR CODE | Refer [Error Code Document](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)
pidDataJson |JSON DATA | The device returns PID DATA of Captured Fingerprint. this parameter contains converted JSON pidData of XML pidData
pidDataXML | XML DATA | pidData Captured Fingerprint
rdServicePackage | Device Package


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

## Would you like to support me?

<a href="https://www.buymeacoffee.com/senthalan2" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-red.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>
