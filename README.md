# react-native-rdservice-fingerprintscanner

React Native library to easily integrate Fingerprint Device support in your app (for UIDAI Aadhaar based secure authentication in India). It is only for Android Devices.

As per [UIDAI](https://uidai.gov.in/) (Aadhaar) guidelines, only registered biometric devices can be used for Aadhaar Authentication. These devices come with RDService drivers (usually available on PlayStore) that exposes a standard API.

This library makes it easy to work with all such devices so that your app can search for installed drivers and get the fingerprint data after a scan.

For reference, you may check out the ([Aadhaar Registered Devices by UIDAI](https://uidai.gov.in/images/resource/Aadhaar_Registered_Devices_2_0_4.pdf)).




## Installation

```sh
npm install react-native-rdservice-fingerprintscanner
```

## Usage

```js
import { multiply } from "react-native-rdservice-fingerprintscanner";

// ...

const result = await multiply(3, 7);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
