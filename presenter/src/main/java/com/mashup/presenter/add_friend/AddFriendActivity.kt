package com.mashup.presenter.add_friend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.ui.add_friend.AddFriendContents
import com.mashup.presenter.ui.add_friend.AddFriendTitles
import com.mashup.presenter.ui.common.ChinChinConfirmButton
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                AddFriendScreen(
                    onActivityFinish = { finish() },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendPreview() {
    AddFriendScreen()
}

@Composable
fun AddFriendScreen(
    onActivityFinish: () -> Unit = {},
) {
    var name by rememberSaveable { mutableStateOf("") }
    var birth by rememberSaveable { mutableStateOf("") }
    var group by rememberSaveable { mutableStateOf("") }

    val isEnable = name.isNotEmpty() && birth.isNotEmpty() && group.isNotEmpty()

    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        ChinChinToolbar(title = "친구 추가하기") {
            onActivityFinish()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp),
        ) {
            Column {
                AddFriendTitles()
                Spacer(modifier = Modifier.height(32.dp))
                AddFriendContents(
                    name = name,
                    onNameValueChanged = { name = it },
                    birth = birth,
                    onBirthValueChanged = { birth = it },
                    group = group,
                    onGroupValueChanged = { group = it },
                )
            }

            Column {
                ChinChinConfirmButton(isEnable = isEnable, buttonText = "친구 취향 기록하기") {}
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
