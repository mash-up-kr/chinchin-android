package com.mashup.chinchin.presenter.ui.set_group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.chinchin.presenter.main.model.GroupInfoUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinGrayTextField
import com.mashup.chinchin.presenter.ui.theme.*


@Composable
fun GroupRadioButtons(
    selectedGroup: GroupInfoUiModel?,
    onChangeState: (GroupInfoUiModel) -> Unit = {},
    groups: List<GroupInfoUiModel> = emptyList(),
    setShowDialog: (Boolean) -> Unit
) {
    // FIXME: 로직 추가시 상태값은 최상위에서 관리되도록 변경해야함.
    val isSelectedItem: (GroupInfoUiModel) -> Boolean = { selectedGroup == it }

    LazyColumn {
        items(groups) { group ->
            GroupRadioButton(group, isSelectedItem, onChangeState)
            Divider(
                color = Gray_300,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 24.dp)
            )
        }
        item {
            NewGroupButton(setShowDialog)
        }
    }
}

@Composable
fun GroupRadioButton(
    group: GroupInfoUiModel,
    isSelectedItem: (group: GroupInfoUiModel) -> Boolean = { false },
    onChangeState: (group: GroupInfoUiModel) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .selectable(
                selected = isSelectedItem(group),
                onClick = { onChangeState(group) },
                role = RadioButton
            )
            .padding(horizontal = 24.dp, vertical = 15.dp)
    ) {
        RadioButton(
            selected = isSelectedItem(group),
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary_2,
                unselectedColor = Gray_300,
                disabledColor = Gray_300
            )
        )
        Text(
            text = group.groupName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        )
    }
}

@Composable
fun NewGroupButton(setShowDialog: (Boolean) -> Unit) {

    Column {
        Box(
            modifier = Modifier
                .clickable(
                    onClick = { setShowDialog(true) }
                )
                .padding(horizontal = 24.dp, vertical = 15.dp)
        ) {
            Text(
                text = "+ 새 그룹 추가",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                fontWeight = FontWeight.Bold,
                color = Primary_2
            )
        }
        Divider(
            color = Gray_300,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
        )

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddGroupDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    addGroup: (String) -> Unit = {}
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = White,
                shape = RoundedCornerShape(8.dp)
            ) {
                AddGroupDialogContent(setShowDialog, addGroup)
            }
        }
    }
}

@Composable
fun AddGroupDialogContent(setShowDialog: (Boolean) -> Unit, addGroup: (String) -> Unit = {}) {
    var groupName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "새 그룹 추가하기",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 11.dp)
        )
        ChinChinGrayTextField(
            value = groupName,
            onValueChange = { groupName = it },
            placeHolder = "그룹명을 작성해주세요",
            paddingValues = PaddingValues(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 13.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    setShowDialog(false)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "취소하기",
                    fontWeight = FontWeight.Bold,
                    color = Gray_500,
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    setShowDialog(false)
                    addGroup(groupName)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "추가하기",
                    fontWeight = FontWeight.Bold,
                    color = Primary_2,
                    fontSize = 16.sp
                )
            }
        }
    }
}
