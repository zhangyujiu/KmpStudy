package com.kmp.demo.datastore

import okio.Path
import okio.Path.Companion.toPath
import java.io.File

actual fun producePath(): Path {
    val file = File(System.getProperty("java.io.tmpdir"), dataStoreFileName)
    return file.absolutePath.toPath()
}