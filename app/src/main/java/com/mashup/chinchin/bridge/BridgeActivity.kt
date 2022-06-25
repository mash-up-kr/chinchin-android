package com.mashup.chinchin.bridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mashup.chinchin.MainActivity
import com.mashup.chinchin.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 개발용 페이지입니다.
 * 개발 완료되면 사라질 예정
 * */
@AndroidEntryPoint
class BridgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BridgeList(items = arrayListOf(
                        MainActivity::class.java,
                        BridgeActivity::class.java,
                    ))
                }
            }
        }
    }
}
