package com.mashup.chinchin.presenter.connect_friend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.NormalDialog
import com.mashup.chinchin.presenter.ui.connect_friend.ConnectFriendSearchBar
import com.mashup.chinchin.presenter.ui.connect_friend.TotalFriendList
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme

class ConnectFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FIXME: ViewModel 추가 되면 이관
        val oldFriend = intent.extras?.get(OLD_FRIEND) as? FriendUiModel ?: return

        setContent {
            ChinchinTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    ConnectFriendScreen(
                        totalFriends = List(25) {
                            FriendUiModel("안경무 $it", "goood")
                        }
                    ) {
                        finish()
                    }
                }
            }
        }
    }

    companion object {
        const val OLD_FRIEND = "OLD_FRIEND"
    }
}

@Composable
fun ConnectFriendScreen(
    totalFriends: List<FriendUiModel> = emptyList(),
    finishActivity: () -> Unit = {},
) {
    val (searchText, onSearchTextChanged) = remember { mutableStateOf("") }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val selectedFriend = remember { mutableStateOf(FriendUiModel("", "")) }

    Column {
        ChinChinToolbar(
            title = "모든 친구 리스트",
        ) {
            finishActivity()
        }
        ConnectFriendSearchBar(
            value = searchText,
            onValueChange = onSearchTextChanged,
            placeHolder = "친구 이름을 검색해보세요."
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ChinChinText(text = "전체", highlightText = "${totalFriends.size}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TotalFriendList(friends = totalFriends, onClickCard = {
            selectedFriend.value = it
            setShowDialog(true)
        })
    }

    if (showDialog) {
        NormalDialog(
            titleText = "${selectedFriend.value.name}에게 연결할까요?",
            onClickSuccess = {
                // TODO: 친구 상세로 넘어가도록
                setShowDialog(false)
            },
            onClickCancel = { setShowDialog(false) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChinchinTheme {
        ConnectFriendScreen()
    }
}