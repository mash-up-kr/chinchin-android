package com.mashup.chinchin.presenter.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.ui.theme.*

@Composable
fun ChinChinToolbar(
    title: String,
    isActiveConfirmButton: Boolean = false,
    isActiveBackButton: Boolean = true,
    isAbleConfirmButton: Boolean = false,
    onConfirmButtonClick: () -> Unit = {},
    onBackButtonClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(White),
    ) {
        val (backButtonRef, confirmButtonRef, textRef) = createRefs()

        if (isActiveBackButton) {
            IconButton(
                onClick = { onBackButtonClick() },
                modifier = Modifier.constrainAs(backButtonRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
            }
        }

        if (isActiveConfirmButton) {
            IconButton(
                onClick = { onConfirmButtonClick() },
                modifier = Modifier.constrainAs(confirmButtonRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                enabled = isAbleConfirmButton
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_check),
                    contentDescription = "confirm button",
                    tint = if (isAbleConfirmButton) Black else Gray_400
                )
            }
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
fun ChinChinText(text: String, highlightText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = highlightText,
            modifier = Modifier.padding(start = 8.dp),
            color = Primary_2,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ChinChinButton(
    icon: Int,
    buttonText: String,
    modifier: Modifier = Modifier,
    buttonColor: Color = Gray_400,
    onButtonClick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = { onButtonClick() },
        modifier = modifier.defaultMinSize(1.dp, 1.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 7.dp),
        border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(buttonColor)),
        shape = RoundedCornerShape(8.dp),
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
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun ChinChinConfirmButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    radius: Dp = 10.dp,
    isEnable: Boolean = false,
    onButtonClick: () -> Unit,
) {
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(radius),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (isEnable) Primary_1 else Gray_300),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        enabled = isEnable,
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = if (isEnable) Gray_800 else Gray_500,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ChinChinTitleAndTextFieldButton(
    title: String,
    iconRes: Int,
    placeHolder: String = "",
    text: String = "",
    onButtonClick: () -> Unit = {}
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
        )
        ChinChinTextFieldButton(iconRes = iconRes, placeHolder = placeHolder, text = text) {
            onButtonClick()
        }
    }
}

@Composable
fun ChinChinTextFieldButton(
    iconRes: Int,
    placeHolder: String,
    text: String,
    onButtonClick: () -> Unit = {}
) {
    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Gray_100),
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
            Text(
                text = text.ifEmpty { placeHolder },
                color = if (text.isEmpty()) Gray_400 else Gray_800,
                fontSize = 14.sp
            )

            Icon(
                painterResource(id = iconRes),
                "",
                tint = Gray_500
            )
        }
    }
}

@Composable
fun ChinChinActingButton(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 20.dp),
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Gray_800,
        )
    }
}

@Composable
fun ChinChinQuestionCard(
    index: Int,
    question: String,
    onQuestionChanged: ((Int, String) -> Unit)? = null,
    answer: String,
    onAnswerChanged: ((Int, String) -> Unit)? = null,
    cardState: ChinChinQuestionCardState = ChinChinQuestionCardState.FRIEND_REPLY,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = getChinChinQuestionCardBackgroundColor(cardState),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChinChinQuestionCardNumberIcon(number = index, cardState = cardState)
                if (cardState == ChinChinQuestionCardState.EDIT_MODE) {
                    BasicTextField(
                        value = question,
                        onValueChange = { onQuestionChanged?.invoke(index, it) },
                        textStyle = TextStyle(
                            color = Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        maxLines = 2,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(),
                    )
                } else {
                    Text(
                        text = question,
                        color = getChinChinQuestionCardTextColor(cardState),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 0.dp,
                backgroundColor = White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp)
            ) {
                if (cardState == ChinChinQuestionCardState.EDIT_MODE) {
                    BasicTextField(
                        value = answer,
                        onValueChange = { onAnswerChanged?.invoke(index, it) },
                        textStyle = TextStyle(
                            color = Gray_500,
                            fontSize = 14.sp
                        ),
                        maxLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        decorationBox = { innerTextField ->
                            if (answer.isBlank()) {
                                Text(
                                    text = "답변을 적어보세요", modifier = Modifier.fillMaxWidth(),
                                    color = Gray_500,
                                    fontSize = 14.sp,
                                )
                            }
                            innerTextField()
                        },
                    )
                } else {
                    Text(
                        text = answer,
                        color = Gray_800,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ChinChinQuestionCardNumberIcon(number: Int, cardState: ChinChinQuestionCardState) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(20.dp), onDraw = {
            drawCircle(color = getChinChinQuestionCardNumberIconBackgroundColor(cardState))
        })
        Text(
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = getChinChinQuestionCardTextColor(cardState)
        )
    }
}

@Composable
fun ChinChinGrayTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String = "",
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .clip(RoundedCornerShape(8.dp))
            .height(48.dp)
            .background(Gray_100),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Gray_800
        ),
        cursorBrush = SolidColor(Gray_800),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeHolder,
                        color = Gray_400,
                        fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        },
    )
}

private fun getChinChinQuestionCardBackgroundColor(cardState: ChinChinQuestionCardState): Color =
    //TODO STATE 별 컬러 정리 필요함
    when (cardState) {
        ChinChinQuestionCardState.FRIEND_REPLY,
        ChinChinQuestionCardState.INPUT_COMPLETE,
        -> {
            Primary_1
        }
        ChinChinQuestionCardState.EXPECT_INCOMPLETE_REPLY,
        ChinChinQuestionCardState.INPUT_INCOMPLETE,
        ChinChinQuestionCardState.EDIT_MODE,
        ChinChinQuestionCardState.DELETE_MODE,
        -> {
            Secondary_1
        }
        ChinChinQuestionCardState.EXPECT_COMPLETE_REPLY -> {
            Gray_400
        }
    }

private fun getChinChinQuestionCardNumberIconBackgroundColor(cardState: ChinChinQuestionCardState): Color =
    //TODO STATE 별 컬러 정리 필요함
    when (cardState) {
        ChinChinQuestionCardState.FRIEND_REPLY,
        ChinChinQuestionCardState.INPUT_COMPLETE,
        -> {
            Primary_2
        }
        ChinChinQuestionCardState.EXPECT_INCOMPLETE_REPLY,
        ChinChinQuestionCardState.INPUT_INCOMPLETE,
        ChinChinQuestionCardState.EDIT_MODE,
        -> {
            Primary_1
        }
        ChinChinQuestionCardState.EXPECT_COMPLETE_REPLY -> {
            Gray_600
        }
        ChinChinQuestionCardState.DELETE_MODE -> {
            Gray_400
        }
    }

private fun getChinChinQuestionCardTextColor(cardState: ChinChinQuestionCardState): Color =
    //TODO STATE 별 컬러 정리 필요함
    when (cardState) {
        ChinChinQuestionCardState.FRIEND_REPLY,
        ChinChinQuestionCardState.INPUT_COMPLETE,
        ChinChinQuestionCardState.EXPECT_INCOMPLETE_REPLY,
        ChinChinQuestionCardState.INPUT_INCOMPLETE,
        ChinChinQuestionCardState.EDIT_MODE,
        ChinChinQuestionCardState.DELETE_MODE,
        -> {
            Gray_700
        }
        ChinChinQuestionCardState.EXPECT_COMPLETE_REPLY -> {
            Gray_100
        }
    }
