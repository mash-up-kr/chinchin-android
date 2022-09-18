package com.mashup.chinchin.presenter.set_group

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.main.model.GroupInfoUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.set_group.AddGroupDialog
import com.mashup.chinchin.presenter.ui.set_group.GroupRadioButtons
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetGroupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                SetGroupScreen()
            }
        }
    }

    companion object {
        const val EXTRA_GROUP = "EXTRA_GROUP"
    }
}

@Composable
fun SetGroupScreen() {
    // basic
    val viewModel: SetGroupViewModel = hiltViewModel()
    val activity = (LocalContext.current as? Activity)

    // compose state
    val groups = viewModel.groups.observeAsState()
    var selectedGroup: GroupInfoUiModel? by remember {
        mutableStateOf(groups.value?.groups?.get(0))
    }
    val onChangeState: (GroupInfoUiModel) -> Unit = { selectedGroup = it }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    // 초기 데이터
    viewModel.getGroups()

    //onBackButtonClick
    fun makeResultAndFinish() {
        val intent = Intent().apply {
            putExtra(SetGroupActivity.EXTRA_GROUP, selectedGroup)
        }
        activity?.setResult(Activity.RESULT_OK, intent)
        activity?.finish()
    }

    BackHandler(enabled = (selectedGroup != null)) {
        makeResultAndFinish()
    }

    StatusBarColor()
    Column {
        ChinChinToolbar(title = "그룹 지정") {
            makeResultAndFinish()
        }
        GroupRadioButtons(
            selectedGroup = selectedGroup,
            onChangeState = onChangeState,
            groups = viewModel.groups.value?.groups ?: emptyList(),
            setShowDialog = setShowDialog
        )
    }
    AddGroupDialog(showDialog, setShowDialog) { groupName ->
        viewModel.createNewGroup(groupName)
    }
}
