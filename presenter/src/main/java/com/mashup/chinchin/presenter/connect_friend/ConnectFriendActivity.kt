package com.mashup.chinchin.presenter.connect_friend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.NormalDialog
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.connect_friend.ConnectFriendSearchBar
import com.mashup.chinchin.presenter.ui.connect_friend.TotalFriendList
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectFriendActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                ConnectFriendScreen {
                    finish()
                }
            }
        }
    }

    companion object {
        const val FRIEND = "FRIEND"
    }
}

@Composable
fun ConnectFriendScreen(
    finishActivity: () -> Unit = {},
) {
    // basic data
    val context = LocalContext.current
    val viewModel: ConnectFriendViewModel = hiltViewModel()

    // current page state
    val friends = viewModel.friends.observeAsState().value ?: emptyList()
    var searchText by remember { mutableStateOf("") }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val selectedFriend: MutableState<FriendUiModel?> = remember { mutableStateOf(null) }

    // searchBar 의 text 가 변경되면서 화면이 recomposition 된다. 그러면서 이 함수가 계속 호출되는데,
    // onValueChange 에서 실행시켜야하지 않을까..? 일단은 중복 호출되어서 onValueChange 에서는 주석처리함
    viewModel.getFriends(searchText)

    StatusBarColor()
    Column {
        ChinChinToolbar(
            title = "모든 친구 리스트",
        ) {
            finishActivity()
        }
        ConnectFriendSearchBar(
            value = searchText,
            onValueChange = {
                searchText = it
//                viewModel.getFriends(it)
            },
            placeHolder = "친구 이름을 검색해보세요."
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ChinChinText(text = "전체", highlightText = "${friends.size}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TotalFriendList(friends = friends, onClickCard = {
            selectedFriend.value = it
            setShowDialog(true)
        })
    }

    if (showDialog) {
        selectedFriend.value?.let {
            NormalDialog(
                titleText = "${it.name}에게 연결할까요?",
                onClickSuccess = {
                    val intent = Intent(context, FriendDetailActivity::class.java).apply {
                        putExtra(EXTRA_FRIEND_ID, it.id)
                    }
                    context.startActivity(intent)
                    setShowDialog(false)
                },
                onClickCancel = { setShowDialog(false) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChinchinTheme {
        ConnectFriendScreen()
    }
}
