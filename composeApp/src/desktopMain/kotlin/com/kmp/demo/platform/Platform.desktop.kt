package com.kmp.demo.platform

import java.util.UUID

actual fun getUUID(): String {
    return UUID.randomUUID().toString()
}