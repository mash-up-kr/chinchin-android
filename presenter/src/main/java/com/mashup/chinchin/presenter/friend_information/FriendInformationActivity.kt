package com.mashup.chinchin.presenter.friend_information

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.GroupUiModel
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.IS_UPDATED_FRIEND_INFO
import com.mashup.chinchin.presenter.friend_information.model.FriendProfileType
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
        Log.i(TAG, "onCreate")
        val makeResultAndFinish: (Long) -> Unit = { friendId ->
            val intent = Intent(this, FriendDetailActivity::class.java).apply {
                putExtra(EXTRA_FRIEND_ID, friendId)
                putExtra(IS_UPDATED_FRIEND_INFO, true)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
        setContent {
            ChinchinTheme {
                FriendInformationScreen(
                    onActivityFinish = { finish() },
                    makeResultAndFinish = makeResultAndFinish
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    companion object {
        const val TAG = "FriendInformationActivity"
        const val EXTRA_PROFILE_TYPE = "EXTRA_PROFILE_TYPE"
        const val EXTRA_FRIEND = "FRIEND"
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
    makeResultAndFinish: (Long) -> Unit = {},
) {
    // basic
    val viewModel: FriendInformationViewModel = hiltViewModel()

    // compose state
    var friendName by rememberSaveable { mutableStateOf(viewModel.friend?.name ?: "") }
    var birthday by rememberSaveable { mutableStateOf(viewModel.friend?.birthday ?: "") }
    var groupName by rememberSaveable { mutableStateOf(viewModel.friend?.group?.groupName ?: "") }
    var selectedGroupId by rememberSaveable { mutableStateOf(viewModel.friend?.group?.groupId) }

    val isEnable =
        friendName.isNotEmpty() && birthday.isNotEmpty() && selectedGroupId != null
    // 저장완료된 친구 id
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
                    groupName = groupName,
                    onGroupValueChanged = {
                        selectedGroupId = it.groupId
                        groupName = it.groupName
                    },
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
                        group = GroupUiModel(
                            groupName = groupName,
                            groupId = selectedGroupId
                        ),
                    )
                    when (viewModel.profileType) {
                        FriendProfileType.ADD -> viewModel.addFriend(friend)
                        FriendProfileType.UPDATE -> viewModel.updateFriend(friend)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

            friendId.value?.let {
                makeResultAndFinish(it)
            }
        }
    }
}
