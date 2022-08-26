package com.mashup.chinchin.presenter.group_detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.group_detail.EmptyGroupDetail
import com.mashup.chinchin.presenter.ui.group_detail.GroupDetailList
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme

class GroupDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val groupId = intent.extras?.get(FRIEND_GROUP_ID) ?: return

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
    }
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
    /* TODO: 서버 연결 필요 */
    val groupUiModel = GroupDetailUiModel(0, "test", emptyList())
    val context = LocalContext.current

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
