import React from 'react'
import { AsyncJsonFileStorage } from '@aeroxmotion/react-native-async-json-file-storage'

const storage = new AsyncJsonFileStorage('test-json-file-storage')

storage.getAllKeys()

// TODO
export default function App() {
  return <></>
}
