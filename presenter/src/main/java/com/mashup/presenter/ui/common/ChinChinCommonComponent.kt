package com.mashup.presenter.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.presenter.R
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Grey_100
import com.mashup.presenter.ui.theme.Grey_400
import com.mashup.presenter.ui.theme.Grey_500
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1
import com.mashup.presenter.ui.theme.Primary_2

@Composable
fun ChinChinCommonToolbar(title: String, onBackButtonClick: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
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
            },
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ChinChinCommonText(text: String, highlightText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = highlightText,
            modifier = Modifier.padding(start = 8.dp),
            color = Primary_2,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun ChinChinCommonButton(
    icon: Int,
    buttonText: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = Grey_400,
    onButtonClick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = { onButtonClick() },
        modifier = modifier.defaultMinSize(1.dp, 1.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 11.dp),
        border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(buttonColor))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = buttonColor
            )
            Text(
                text = buttonText,
                color = buttonColor,
                modifier = Modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ChinChinConfirmButton(
    buttonText: String,
    radius: Dp = 10.dp,
    isEnable: Boolean = false,
    onButtonClick: () -> Unit,
) {
    if (isEnable) {
        ChinChinEnableConfirmButton(
            buttonText = buttonText,
            radius = radius,
            onButtonClick = onButtonClick,
        )
    } else {
        ChinChinDisableConfirmButton(
            buttonText = buttonText,
            radius = radius,
            onButtonClick = onButtonClick,
        )
    }
}

@Composable
private fun ChinChinEnableConfirmButton(
    buttonText: String,
    radius: Dp = 10.dp,
    onButtonClick: () -> Unit,
) {
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(radius),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = Grey_800,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ChinChinDisableConfirmButton(
    buttonText: String,
    radius: Dp = 10.dp,
    buttonColor: Color = Grey_500,
    onButtonClick: () -> Unit,
) {
    OutlinedButton(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(radius),
        contentPadding = PaddingValues(vertical = 20.dp),
        border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(buttonColor)),
        modifier = Modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = buttonColor,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
fun ChinChinTitleAndTextFieldButton(title: String, iconRes: Int, placeHolder: String = "", onButtonClick: () -> Unit = {}) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
        )
        ChinChinTextFieldButton(iconRes = iconRes, placeHolder = placeHolder) {
            onButtonClick()
        }
    }
}

@Composable
fun ChinChinTextFieldButton(iconRes: Int, placeHolder: String, onButtonClick: () -> Unit = {}) {
    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Grey_100),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .height(48.dp)
            .defaultMinSize(1.dp, 1.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        contentPadding = PaddingValues(horizontal = 12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // placeHolder
            Text(
                text = placeHolder,
                color = Grey_400,
                fontSize = 14.sp
            )
            Icon(
                painterResource(id = iconRes),
                "",
                tint = Grey_500
            )
        }
    }
}
