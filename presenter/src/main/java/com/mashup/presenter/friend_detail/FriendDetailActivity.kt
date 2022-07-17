package com.mashup.presenter.friend_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.friend_detail.FriendProfile
import com.mashup.presenter.ui.theme.ChinchinTheme

class FriendDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                FriendDetailScreen(friendProfileUiModel = initFriendProfile()) {
                    finish()
                }
            }
        }
    }

    private fun initFriendProfile(): FriendProfileUiModel {
        return FriendProfileUiModel(
            profileUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            name = "매쉬업",
            birthday = "12월 31일",
            groupName = "매쉬업 그룹"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun FriendDetailPreview() {
    FriendDetailScreen(
        FriendProfileUiModel(
            profileUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            name = "매쉬업",
            birthday = "12월 31일",
            groupName = "매쉬업 그룹"
        )
    )
}

@Composable
fun FriendDetailScreen(
    friendProfileUiModel: FriendProfileUiModel,
    finishActivity: () -> Unit = {}
) {
    Column {
        ChinChinToolbar(title = "") {
            finishActivity()
        }
        Row(
            modifier = Modifier
                .height(142.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 15.dp),
        ) {
            FriendProfile(friendProfileUiModel)
        }
    }

}