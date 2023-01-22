package com.alex.lingvatranslate

import android.app.Application
import com.alex.lingvatranslate.data.AppContainer
import com.alex.lingvatranslate.data.DefaultAppContainer

class LingvaTranslateApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}