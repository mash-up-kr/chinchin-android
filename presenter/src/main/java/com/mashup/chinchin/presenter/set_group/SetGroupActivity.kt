package com.mashup.chinchin.presenter.set_group

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.set_group.GroupRadioButtons
import com.mashup.chinchin.presenter.ui.set_group.NewGroupButton
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme

class SetGroupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                val groups = listOf("지정안함", "그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "그룹6")
                val selectedGroup: MutableState<String> = remember { mutableStateOf("") }

                SetGroupScreen(selectedGroup, groups) {
                    val intent = Intent().apply {
                        putExtra(EXTRA_GROUP_NAME, selectedGroup.value)
                    }
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

            }
        }
    }

    companion object {
        const val EXTRA_GROUP_NAME = "EXTRA_GROUP_NAME"
    }
}

@Composable
fun SetGroupScreen(
    selectedGroup: MutableState<String>,
    groups: List<String>,
    finishActivityWithResult: () -> Unit = {}
) {
    val onChangeState: (String) -> Unit = { selectedGroup.value = it }

    Column {
        ChinChinToolbar(title = "그룹 지정") {
            finishActivityWithResult()
        }
        GroupRadioButtons(
            selectedGroup = selectedGroup.value,
            onChangeState = onChangeState,
            groups = groups
        )
    }
}
