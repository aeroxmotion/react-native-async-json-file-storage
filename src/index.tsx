import { NativeModules } from 'react-native';

type ReactNativeAsyncJsonFileStorageType = {
  multiply(a: number, b: number): Promise<number>;
};

const { ReactNativeAsyncJsonFileStorage } = NativeModules;

export default ReactNativeAsyncJsonFileStorage as ReactNativeAsyncJsonFileStorageType;
