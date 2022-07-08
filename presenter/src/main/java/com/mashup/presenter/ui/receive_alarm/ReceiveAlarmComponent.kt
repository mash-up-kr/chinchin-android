package com.mashup.presenter.ui.receive_alarm

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Yellow

@Composable
fun Toolbar(title: String, onBackButtonClick: () -> Unit) {
    val modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)

    ConstraintLayout(
        modifier = modifier,
    ) {
        val (iconRef, textRef) = createRefs()

        IconButton(
            onClick = { onBackButtonClick() },
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
        }

        Text(
            text = title,
            modifier = Modifier.constrainAs(textRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun RequestCountText(requestCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp)
    ) {
        Text(
            text = "요청",
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = "${requestCount}개",
            modifier = Modifier.padding(start = 8.dp),
            color = Yellow,
            fontSize = 18.sp,
        )
    }
}
