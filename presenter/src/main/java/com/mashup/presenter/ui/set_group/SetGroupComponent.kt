package com.mashup.presenter.ui.set_group

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Gray_300
import com.mashup.presenter.ui.theme.Primary_2

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
fun GroupRadioButtons(groups: List<String> = emptyList()) { // TODO: Group data class 만들어서 변경해야함
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

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
fun NewGroupButton(addNewGroup: () -> Unit = {}) {
    Column {
        Box(
            modifier = Modifier
                .clickable(
                    onClick = { addNewGroup() }
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