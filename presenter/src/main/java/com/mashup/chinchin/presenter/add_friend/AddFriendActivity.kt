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
import com.mashup.chinchin.presenter.add_friend.model.FriendProfileType
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.add_friend.AddFriendContents
import com.mashup.chinchin.presenter.ui.add_friend.AddFriendTitles
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileType =
            intent.extras?.get(EXTRA_PROFILE_TYPE) as? FriendProfileType ?: FriendProfileType.CREATE
        // FIXME: ViewModel 추가 되면 이관
        val newFriend = intent.extras?.get(NEW_FRIEND) as? FriendUiModel // TODO: friendProfileUiModel?로 바꿔야하네..

        setContent {
            ChinchinTheme {
                AddFriendScreen(
                    profileType = profileType,
                    onActivityFinish = { finish() },
                    newFriend = newFriend,
                )
            }
        }
    }

    companion object {
        const val EXTRA_PROFILE_TYPE = "EXTRA_PROFILE_TYPE"
        const val NEW_FRIEND = "NEW_FRIEND"
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendPreview() {
    AddFriendScreen(newFriend = null)
}

@Composable
fun AddFriendScreen(
    profileType: FriendProfileType = FriendProfileType.CREATE,
    onActivityFinish: () -> Unit = {},
    newFriend: FriendUiModel? = null,
) {
    var friendName by rememberSaveable { mutableStateOf(newFriend?.name ?: "") }
    var birthday by rememberSaveable { mutableStateOf("") }
    var groupName by rememberSaveable { mutableStateOf("") }
    val isEnable = friendName.isNotEmpty() && birthday.isNotEmpty() && groupName.isNotEmpty()

    StatusBarColor()
    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        ChinChinToolbar(
            title = if (profileType == FriendProfileType.CREATE) "친구 추가하기" else "프로필 수정"
        ) {
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
                ChinChinConfirmButton(
                    isEnable = isEnable,
                    buttonText = if (profileType == FriendProfileType.CREATE) "친구 취향 기록하기" else "수정완료"
                ) {}
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
