package com.kmp.demo.platform

import android.os.Build
import java.util.UUID

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getUUID(): String = UUID.randomUUID().toString()