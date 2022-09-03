package com.mashup.chinchin.presenter.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.chinchin.presenter.ui.theme.Gray_500

@Preview(showBackground = true)
@Composable
fun ImageDialogPreView() {
    ImageDialog(
        titleText = "안녕하세요\n친친에 오신 것을 환영해요!",
        subTitleText = "보낸 취향 질문은 수정이 불가능해요",
        confirmText = "친구추가 하러가기",
        cancelText = "다음에 하기",
        onClickConfirm = {},
        onClickCancel = {},
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageDialog(
    @DrawableRes drawableId: Int? = null,
    titleText: String,
    subTitleText: String? = null,
    confirmText: String,
    cancelText: String,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onClickCancel() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
        ) {
            ImageDialogContents(
                drawableId = drawableId,
                titleText = titleText,
                subTitleText = subTitleText,
                confirmText = confirmText,
                cancelText = cancelText,
                onClickConfirm = onClickConfirm,
                onClickCancel = onClickCancel,
            )
        }
    }
}

@Composable
fun ImageDialogContents(
    @DrawableRes drawableId: Int? = null,
    titleText: String,
    subTitleText: String? = null,
    confirmText: String,
    cancelText: String,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(310.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        drawableId?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = titleText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                bottom = if (subTitleText.isNullOrBlank()) 24.dp else 0.dp
            )
        )

        subTitleText?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 9.dp, bottom = 24.dp)
            )
        }

        ChinChinConfirmButton(
            buttonText = confirmText,
            isEnable = true,
            modifier = Modifier
                .width(182.dp),
            radius = 64.dp
        ) {
            onClickConfirm()
        }

        Text(
            text = cancelText,
            fontSize = 16.sp,
            color = Gray_500,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable { onClickCancel() }
        )
    }
}
