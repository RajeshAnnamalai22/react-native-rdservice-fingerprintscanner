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
        console.log(response, 'DEVICE DRIVER FOUND'); // Either The Device connected or not connected response here
      })
      .catch((error) => {
        console.log(error, 'DEVICE DRIVER NOT FOUND'); //Failed to get device information
      });
      
    captureFinger(pidOptions)
      .then((response) => {
        console.log(response, 'FINGER CAPTURE'); // Either The Device Connected or Not Connected Response here
      })
      .catch((e) => {
        console.log(e, 'ERROR_FINGER_CAPTURE'); // Failed to capture the Fingerprint
      });
```

```pidOptions``` is an XML String that you have to pass to ```captureFinger``` method. Refer [UIDAI Document](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)

```Note``` : First, Must be call ```getDeviceInfo()``` method to get fingerprint device info. According to the response of ```getDeviceInfo()``` method, call ```captureFinger()``` Method.

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

## Would you like to support me?

<a href="https://www.buymeacoffee.com/senthalan2" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-red.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>
