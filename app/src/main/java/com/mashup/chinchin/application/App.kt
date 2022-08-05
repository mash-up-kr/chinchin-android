package com.mashup.chinchin.application

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.kakao.sdk.common.KakaoSdk
import com.mashup.chinchin.BuildConfig
import com.mashup.chinchin.data.di.NetworkModule
import com.mashup.chinchin.presenter.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initFlipper()
        initKakaoSDK()
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

    private fun initKakaoSDK() {
        KakaoSdk.init(this, getString(R.string.KAKAO_APP_NATIVE_KEY))
    }
}
