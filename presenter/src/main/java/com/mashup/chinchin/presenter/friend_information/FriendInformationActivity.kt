package com.mashup.chinchin.presenter.friend_information

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.friend_information.model.FriendProfileType
import com.mashup.chinchin.presenter.main.model.GroupInfoUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.friend_information.FriendInformationContents
import com.mashup.chinchin.presenter.ui.friend_information.FriendInformationTitles
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                FriendInformationScreen(
                    onActivityFinish = { finish() },
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
    FriendInformationScreen()
}

@Composable
fun FriendInformationScreen(
    onActivityFinish: () -> Unit = {},
) {
    // basic
    val context = LocalContext.current
    val viewModel: FriendInformationViewModel = hiltViewModel()

    // compose state
    var friendName by rememberSaveable { mutableStateOf(viewModel.newFriend?.name ?: "") }
    var birthday by rememberSaveable { mutableStateOf("") }
    var selectedGroup: GroupInfoUiModel? by remember {
        mutableStateOf(null)
    }
    val isEnable =
        friendName.isNotEmpty() && birthday.isNotEmpty() && selectedGroup?.groupName?.isNotEmpty() == true
    val friendId = viewModel.friendId.observeAsState()

    StatusBarColor()
    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        ChinChinToolbar(
            title = if (viewModel.profileType == FriendProfileType.ADD) "친구 추가하기" else "프로필 수정"
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
                FriendInformationTitles()
                Spacer(modifier = Modifier.height(32.dp))
                FriendInformationContents(
                    friendName = friendName,
                    onNameValueChanged = { friendName = it },
                    birthday = birthday,
                    onBirthValueChanged = { birthday = it },
                    groupName = selectedGroup?.groupName ?: "",
                    onGroupValueChanged = { selectedGroup = it },
                )
            }

            Column {
                ChinChinConfirmButton(
                    isEnable = isEnable,
                    buttonText = if (viewModel.profileType == FriendProfileType.ADD) "친구 취향 기록하기" else "수정완료"
                ) {
                    val friend = FriendUiModel(
                        name = friendName,
                        birthday = birthday,
                        groupName = selectedGroup?.groupName ?: "",
                        groupId = selectedGroup?.groupId,
                    )
                    when (viewModel.profileType) {
                        FriendProfileType.ADD -> viewModel.addFriend(friend)
                        FriendProfileType.UPDATE -> viewModel.updateFriend(friend)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

            if (friendId.value != null) {
                val intent = Intent(context, FriendDetailActivity::class.java).apply {
                    putExtra(EXTRA_FRIEND_ID, friendId.value)
                }
                onActivityFinish()
                context.startActivity(intent)
            }
        }
    }
}
