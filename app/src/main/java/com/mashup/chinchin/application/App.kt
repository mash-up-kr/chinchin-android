package com.mashup.chinchin.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.mashup.chinchin.BuildConfig
import com.mashup.data.di.NetworkModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        //CrimeRepositoryImpl.initialize(this)
        initFlipper()
    }

    private fun initFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this).apply {
                // layout
                addPlugin(InspectorFlipperPlugin(this@App, DescriptorMapping.withDefaults()))
                // network
                addPlugin(NetworkModule.networkFlipperPlugin)
            }.start()
        }
    }
}
