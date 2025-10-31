package com.kmp.demo

import android.app.Application

object Apps {
    lateinit var application: Application
        private set

    fun initialize(application: Application) {
        Apps.application = application
    }
}