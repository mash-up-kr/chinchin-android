package com.mashup.presenter.add_friend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.ui.add_friend.AddFriendContents
import com.mashup.presenter.ui.add_friend.AddFriendEnableConfirmButton
import com.mashup.presenter.ui.add_friend.AddFriendTitles
import com.mashup.presenter.ui.common.ChinChinCommonToolbar
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFriendActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                AddFriendScreen() {
                    finish()
                }
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
fun AddFriendScreen(onActivityFinish: () -> Unit = {}) {
    var friendName: String by rememberSaveable { mutableStateOf("굿") }

    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        ChinChinCommonToolbar(title = "친구 추가하기") {
            onActivityFinish.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 24.dp),
        ) {
            Column{
                AddFriendTitles()
                Spacer(modifier = Modifier.height(32.dp))
                AddFriendContents(
                    friendName = friendName,
                    onValueChanged = { friendName = it }
                )
            }

            AddFriendEnableConfirmButton {}
        }
    }
}
