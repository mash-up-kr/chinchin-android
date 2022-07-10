package com.mashup.presenter.group_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.presenter.ui.group_detail.GroupDetailList
import com.mashup.presenter.ui.group_detail.TotalCountText
import com.mashup.presenter.ui.receive_alarm.Toolbar
import com.mashup.presenter.ui.theme.ChinchinTheme

class GroupDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                GroupDetailScreen(groupDetailUiModels = initGroupDetails())
                finish()
            }
        }
    }

    private fun initGroupDetails(): List<GroupDetailUiModel> {
        val groupDetailUiModels = mutableListOf<GroupDetailUiModel>()
        repeat(20) {
            groupDetailUiModels.add(
                GroupDetailUiModel(
                    userName = "혜진",
                    profileUrl = "good"
                )
            )
        }
        return groupDetailUiModels.toList()
    }
}

@Preview
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
        Toolbar(
            title = "매쉬업",
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            finishActivity()
        }

        TotalCountText(groupDetailUiModels.size)
        GroupDetailList(groupDetailUiModels)
    }

}