package com.mashup.presenter.ui.set_group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mashup.presenter.ui.theme.*

@Preview
@Composable
fun PreviewSetGroup() {
    ChinchinTheme {
        val groups = listOf("지정안함", "그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "그룹6")

        Column {
            GroupRadioButtons(groups = groups)
            NewGroupButton()
        }
    }
}

@Composable
fun GroupRadioButtons(
    selectedGroup: String = "",
    onChangeState: (String) -> Unit = {},
    groups: List<String> = emptyList()// TODO: Group data class 만들어서 변경해야함
) {
    // FIXME: 로직 추가시 상태값은 최상위에서 관리되도록 변경해야함.
    val isSelectedItem: (String) -> Boolean = { selectedGroup == it }

    Column {
        groups.forEach { group ->
            GroupRadioButton(group, isSelectedItem, onChangeState)
            Divider(
                color = Gray_300,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun GroupRadioButton(
    group: String,
    isSelectedItem: (group: String) -> Boolean = { false },
    onChangeState: (group: String) -> Unit = {}
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
            text = group,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        )
    }
}

@Composable
fun NewGroupButton() {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

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

        AddGroupDialog(showDialog, setShowDialog)
    }
}

@Composable
fun AddGroupDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {}
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                color = White,
                shape = RoundedCornerShape(8.dp)
            ) {
                AddGroupDialogContent(setShowDialog)
            }
        }
    }
}

@Composable
fun AddGroupDialogContent(setShowDialog: (Boolean) -> Unit, addGroup: () -> Unit = {}) {
    var groupName by remember { mutableStateOf(TextFieldValue()) }

    Column {
        Text(
            text = "새 그룹 추가 ",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, start = 16.dp, bottom = 14.dp)
                .wrapContentSize()
        )

        TextField(
            value = groupName,
            onValueChange = { groupName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Black,
                cursorColor = Black,
                disabledTextColor = Gray_400,
                backgroundColor = Gray_100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(top = 13.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    setShowDialog(false)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "취소하기",
                    fontWeight = FontWeight.Bold,
                    color = Gray_500
                )
            }
            Button(
                onClick = {
                    setShowDialog(false)
                    addGroup() // TODO: 그룹 추가하기~!!!!
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "추가하기",
                    fontWeight = FontWeight.Bold,
                    color = Primary_2
                )
            }
        }

    }
}