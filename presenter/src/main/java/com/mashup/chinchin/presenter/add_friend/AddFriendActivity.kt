package com.mashup.chinchin.presenter.add_friend

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
import com.mashup.chinchin.presenter.ui.add_friend.AddFriendContents
import com.mashup.chinchin.presenter.ui.add_friend.AddFriendTitles
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
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
    var friendName by rememberSaveable { mutableStateOf("") }
    var birthday by rememberSaveable { mutableStateOf("") }
    var groupName by rememberSaveable { mutableStateOf("") }

    val isEnable = friendName.isNotEmpty() && birthday.isNotEmpty() && groupName.isNotEmpty()

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
                    friendName = friendName,
                    onNameValueChanged = { friendName = it },
                    birthday = birthday,
                    onBirthValueChanged = { birthday = it },
                    groupName = groupName,
                    onGroupValueChanged = { groupName = it },
                )
            }

            Column {
                ChinChinConfirmButton(isEnable = isEnable, buttonText = "친구 취향 기록하기") {}
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}