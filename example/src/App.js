import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import {
  getDeviceInfo,
  captureFinger,
} from 'react-native-rdservice-fingerprintscanner';

export default function App() {
  const start = () => {
    getDeviceInfo()
      .then((res) => {
        console.log(res, 'DEVICE INFO');
      })
      .catch((e) => {
        console.log(e, 'ERROR_DEVICE_INFO ');
      });
  };

  const capture = () => {
    captureFinger()
      .then((res) => {
        console.log(res, 'FINGER CAPTURE');
      })
      .catch((e) => {
        console.log('ERROR_FINGER_CAPTURE');
      });
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={start}
        style={{
          padding: 10,
          marginVertical: 5,
          paddingHorizontal: 30,
          backgroundColor: 'tomato',
          borderRadius: 10,
        }}
      >
        <Text
          style={{
            fontSize: 16,
            color: '#FFFFFF',
          }}
        >
          Start
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={capture}
        style={{
          padding: 10,
          marginVertical: 5,
          paddingHorizontal: 30,
          backgroundColor: 'tomato',
          borderRadius: 10,
        }}
      >
        <Text
          style={{
            fontSize: 16,
            color: '#FFFFFF',
          }}
        >
          Capture
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
