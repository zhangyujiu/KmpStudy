package com.kmp.demo.platform

import platform.Foundation.NSUUID
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun getUUID(): String {
    return NSUUID().UUIDString()
}