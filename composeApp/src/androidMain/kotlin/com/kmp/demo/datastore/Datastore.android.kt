package com.kmp.demo.datastore

import com.kmp.demo.Apps
import okio.Path
import okio.Path.Companion.toPath

actual fun producePath(): Path {
    return Apps.application.filesDir.resolve(dataStoreFileName).absolutePath.toPath()
}