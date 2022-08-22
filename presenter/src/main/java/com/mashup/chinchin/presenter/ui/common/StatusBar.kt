package com.mashup.chinchin.presenter.ui.common

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBarColor() {
    val activity = LocalView.current.context as Activity
    val backgroundArgb = MaterialTheme.colors.background.toArgb()
    activity.window.statusBarColor = backgroundArgb

    val wic = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
    wic.isAppearanceLightStatusBars = true
}