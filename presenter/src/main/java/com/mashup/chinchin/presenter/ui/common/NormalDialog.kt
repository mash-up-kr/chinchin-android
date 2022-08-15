package com.mashup.chinchin.presenter.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Primary_2
import com.mashup.chinchin.presenter.ui.theme.White

@Preview(showBackground = true)
@Composable
fun NormalDialogPreview() {
    NormalDialog("이기존에게 연락할까요?", {}, {})
}

@Composable
fun NormalDialog(
    titleText: String,
    onClickSuccess: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Dialog(onDismissRequest = { onClickCancel() }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
        ) {
            NormalDialogContents(
                titleText = titleText,
                onClickSuccess = onClickSuccess,
                onClickCancel = onClickCancel,
            )
        }
    }
}

@Composable
fun NormalDialogContents(
    titleText: String,
    onClickSuccess: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(312.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = titleText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 34.dp, bottom = 25.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NormalDialogButton(
                buttonText = "아니요",
                buttonTextColor = Gray_500,
                Modifier.weight(1f),
                onClickButton = onClickCancel,
            )

            NormalDialogButton(
                buttonText = "네",
                buttonTextColor = Primary_2,
                Modifier.weight(1f),
                onClickButton = onClickSuccess,
            )
        }
    }
}

@Composable
fun NormalDialogButton(
    buttonText: String,
    buttonTextColor: Color,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
) {
    Button(
        onClick = { onClickButton() },
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        modifier = modifier
            .fillMaxHeight(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight.Bold,
            color = buttonTextColor,
            fontSize = 16.sp,
        )
    }
}
