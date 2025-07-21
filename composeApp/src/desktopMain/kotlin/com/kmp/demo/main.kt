package com.kmp.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main(){
    initLogger()
    Napier.d("Desktop App Running...")
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KmpStudy",
        ) {
            App()
        }
    }
}

fun initLogger() {
    Napier.base(DebugAntilog()) // DebugAntilog 默认输出到 JVM 的 System.out
}