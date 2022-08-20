package com.mashup.chinchin.presenter.group_detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.group_detail.EmptyGroupDetail
import com.mashup.chinchin.presenter.ui.group_detail.GroupDetailList
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import java.lang.Exception

class GroupDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val group = intent.extras?.get(FRIEND_GROUP) as? FriendGroupUiModel ?: return

        setContent {
            ChinchinTheme {
                GroupDetailScreen(group = group) {
                    finish()
                }
            }
        }
    }

    companion object {
        const val FRIEND_GROUP = "FRIEND_GROUP"
    }
}

@Preview(showBackground = true)
@Composable
fun GroupDetailPreview() {
    GroupDetailScreen()
}

@Composable
fun GroupDetailScreen(
    group: FriendGroupUiModel = FriendGroupUiModel("test", emptyList()),
    finishActivity: () -> Unit = {}
) {
    val context = LocalContext.current

    Column {
        ChinChinToolbar(
            title = group.name,
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
            ChinChinText(text = "전체", highlightText = "${group.friends.size}")
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
        if (group.friends.isEmpty()) {
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
                friends = group.friends
            )
        }
    }
}
