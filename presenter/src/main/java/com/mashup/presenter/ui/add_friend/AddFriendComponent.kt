package com.mashup.presenter.ui.add_friend

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.set_group.SetGroupActivity
import com.mashup.presenter.set_group.SetGroupActivity.Companion.EXTRA_GROUP_NAME
import com.mashup.presenter.ui.common.ChinChinTitleAndTextFieldButton
import com.mashup.presenter.ui.theme.*
import java.util.*

@Composable
fun AddFriendTitles() {
    Column {
        Text(
            text = "친구의 인적사항 및 그룹을 지정해주세요",
            fontSize = 18.sp,
            color = Gray_800,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "아래 항목들은 언제든지 수정가능합니다.",
            fontSize = 12.sp,
            color = Gray_500,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun AddFriendContents(
    friendName: String,
    birthday: String,
    groupName: String,
    onNameValueChanged: (String) -> Unit,
    onBirthValueChanged: (String) -> Unit,
    onGroupValueChanged: (String) -> Unit,
) {
    val context = LocalContext.current
    val dialog = BirthdayDatePickerDialog(
        date = birthday,
        onValueChanged = onBirthValueChanged
    )
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode != Activity.RESULT_OK) {
            return@rememberLauncherForActivityResult
        }
        //GET TEXT ARRAY FROM VOICE INTENT
        val result = it.data?.getStringExtra(EXTRA_GROUP_NAME)

        if (result != null) {
            onGroupValueChanged(result)
        }
    }

    Column {
        AddFriendNameComponent(friendName = friendName, onValueChanged = onNameValueChanged)
        Spacer(modifier = Modifier.height(16.dp))
        ChinChinTitleAndTextFieldButton(
            title = "생일 *",
            iconRes = R.drawable.icon_calendar,
            onButtonClick = { dialog.show() },
            text = birthday
        )
        Spacer(modifier = Modifier.height(16.dp))
        ChinChinTitleAndTextFieldButton(
            title = "친친 그룹 *",
            iconRes = R.drawable.icon_arrow,
            placeHolder = "그룹 지정하기",
            text = groupName,
            onButtonClick = {
                launcher.launch(
                    Intent(context, SetGroupActivity::class.java)
                )
            }
        )
    }
}

@Composable
fun AddFriendNameComponent(friendName: String, onValueChanged: (String) -> Unit) {
    Column {
        Text(
            text = "이름 *",
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
        )
        TextField(
            value = friendName,
            onValueChange = { onValueChanged(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Gray_100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(48.dp),
            placeholder = { Text(text = "친구 이름", color = Gray_400) },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun BirthdayDatePickerDialog(date: String, onValueChanged: (String) -> Unit): DatePickerDialog {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    return DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onValueChanged("${mMonth + 1}월 ${mDayOfMonth}일")
        }, year, month, day
    )
}

