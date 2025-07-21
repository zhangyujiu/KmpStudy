package com.kmp.demo

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class KmpApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    fun initLogger() {
        Napier.base(DebugAntilog())
    }
}