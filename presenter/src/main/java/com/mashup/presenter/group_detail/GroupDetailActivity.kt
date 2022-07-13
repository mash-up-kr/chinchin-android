package com.mashup.presenter.group_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.R
import com.mashup.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.presenter.ui.common.ChinChinCommonButton
import com.mashup.presenter.ui.common.ChinChinCommonText
import com.mashup.presenter.ui.common.ChinChinCommonToolbar
import com.mashup.presenter.ui.group_detail.GroupDetailList
import com.mashup.presenter.ui.theme.ChinchinTheme

class GroupDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                GroupDetailScreen(groupDetailUiModels = initGroupDetails()) {
                    finish()
                }
            }
        }
    }

    private fun initGroupDetails(): List<GroupDetailUiModel> {
        val groupDetailUiModels = mutableListOf<GroupDetailUiModel>()
        repeat(20) {
            groupDetailUiModels.add(
                GroupDetailUiModel(
                    userName = "김매쉬",
                    profileUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
                )
            )
        }
        return groupDetailUiModels.toList()
    }
}

@Preview(showBackground = true)
@Composable
fun GroupDetailPreview() {
    GroupDetailScreen()
}

@Composable
fun GroupDetailScreen(
    groupDetailUiModels: List<GroupDetailUiModel> = listOf(),
    finishActivity: () -> Unit = {}
) {
    Column {
        ChinChinCommonToolbar(
            title = "매쉬업",
        ) {
            finishActivity()
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ChinChinCommonText(text = "전체", highlightText = "${groupDetailUiModels.size}")
            ChinChinCommonButton(icon = R.drawable.icon_user_more1, buttonText = "친구 추가")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GroupDetailList(groupDetailUiModels)
    }
}
