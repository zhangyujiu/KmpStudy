package com.kmp.demo.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getUUID(): String