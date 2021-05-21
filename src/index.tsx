import { NativeModules } from 'react-native'

type ObjectEntry =
  | string
  | number
  | boolean
  | null
  | ObjectEntry[]
  | { [key: string]: ObjectEntry }

type AsyncJsonFileStorageType = {
  getAllKeys(storageName: string): Promise<string[]>
  multiGet(storageName: string, keys: string[]): Promise<ObjectEntry[]>
  multiSet(
    storageName: string,
    entries: Record<string, ObjectEntry>
  ): Promise<void>
  multiRemove(storageName: string, keys: string[]): Promise<void>
  clear(storageName: string): Promise<void>
}

export const AsyncJsonFileStorageNative: AsyncJsonFileStorageType =
  NativeModules.AsyncJsonFileStorage

export class AsyncJsonFileStorage {
  constructor(public storageName: string) {}

  //#region ConvenienceMethods
  async getItem(key: string) {
    const [value] = await this.multiGet([key])
    return value
  }

  async setItem(key: string, value: ObjectEntry) {
    return this.multiSet({ [key]: value })
  }

  async removeItem(key: string) {
    return this.multiRemove([key])
  }
  //#endregion

  //#region NativeMethods
  getAllKeys() {
    return AsyncJsonFileStorageNative.getAllKeys(this.storageName)
  }

  multiGet(keys: string[]) {
    return AsyncJsonFileStorageNative.multiGet(this.storageName, keys)
  }

  multiSet(entries: Record<string, ObjectEntry>) {
    return AsyncJsonFileStorageNative.multiSet(this.storageName, entries)
  }

  multiRemove(keys: string[]) {
    return AsyncJsonFileStorageNative.multiRemove(this.storageName, keys)
  }

  clear() {
    return AsyncJsonFileStorageNative.clear(this.storageName)
  }
  //#endregion
}
