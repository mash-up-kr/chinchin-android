package com.mashup.chinchin.presenter.group_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.group_detail.GroupDetailActivity.Companion.TAG
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.group_detail.EmptyGroupDetail
import com.mashup.chinchin.presenter.ui.group_detail.GroupDetailList
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                GroupDetailScreen {
                    finish()
                }
            }
        }
    }

    companion object {
        const val FRIEND_GROUP_ID = "FRIEND_GROUP_ID"
        const val TAG = "GroupDetailActivity"
    }
}

private fun showToast(context: Context) {
    Toast.makeText(context, "잘못된 그룹 아이디 입니다", Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun GroupDetailPreview() {
    GroupDetailScreen()
}

@Composable
fun GroupDetailScreen(
    finishActivity: () -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel: GroupDetailViewModel = hiltViewModel()
    viewModel.groupId?.let {
        viewModel.getGroupDetail(it)
    } ?: run {
        Log.i(TAG, "GroupId is null")
        showToast(context)
        finishActivity()
    }

    val groupUiModel = viewModel.groupDetail.observeAsState().value ?: run {
        Log.i(TAG, "GroupDetailScreen: groupUiModel is null")
        return
    }

    StatusBarColor()
    Column {
        ChinChinToolbar(
            title = groupUiModel.groupName,
        ) {
            finishActivity()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ChinChinText(text = "전체", highlightText = "${groupUiModel.friends.size}")
            ChinChinButton(
                icon = R.drawable.icon_user_more1,
                buttonText = "친구 추가",
                onButtonClick = {
                    val intent = Intent(context, AddFriendActivity::class.java).apply {
                        // TODO: 그룹 이름 넘기기
                    }
                    context.startActivity(intent)
                })
        }
        if (groupUiModel.friends.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyGroupDetail()
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))
            GroupDetailList(
                onClickCard = {
                    val intent = Intent(context, FriendDetailActivity::class.java).apply {
                        putExtra(EXTRA_FRIEND_ID, it.id)
                    }
                    context.startActivity(intent)
                },
                friends = groupUiModel.friends
            )
        }
    }
}
