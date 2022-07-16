package com.mashup.presenter.set_group

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.set_group.GroupRadioButtons
import com.mashup.presenter.ui.set_group.NewGroupButton
import com.mashup.presenter.ui.theme.ChinchinTheme

class SetGroupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                val groups = listOf("지정안함", "그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "그룹6")

                SetGroupScreen(groups) {
                    finish()
                }
            }
        }
    }
}

@Composable
fun SetGroupScreen(groups: List<String>, finishActivity: () -> Unit = {}) {
    Column {
        ChinChinToolbar(title = "그룹지정") {
            finishActivity()
        }
        GroupRadioButtons(groups = groups)
        NewGroupButton()
    }
}