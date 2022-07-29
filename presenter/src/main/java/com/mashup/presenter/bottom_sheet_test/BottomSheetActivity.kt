package com.mashup.presenter.bottom_sheet_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.ui.theme.ChinchinTheme
import kotlinx.coroutines.launch
import androidx.compose.material.rememberBottomSheetState as rememberBottomSheetState


class BottomSheetActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                //bottom sheet 상태 기억
                val sheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed,
                    //bottom sheet 애니메이션 추가
                   /* animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy
                    )*/
                )
                // 스캐폴드 직접 전달 할수 없으므로 스캐폴드 상태에 bottom sheet 상태를 전달한다.
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState,
                )
                //코루틴 스코프를 기억하도록 하고 composition 라이프 사이클 동안 작동한다. 이 컴포지션을 떠나는 즉시
                //스코프에서 실행되는 모든것을 취소한다 -> scope.launch() 안에있는 모든 동작을 취소함
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "bottom sheet", fontSize = 60.sp)
                        }
                    },
                    sheetBackgroundColor = Green,
                    sheetPeekHeight = 20.dp // 조금보이는 height 수정
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        //expand 함수는 수행하는데 시간이 좀걸리기 때문에 코루틴을 사용해야한다.
                        //코루틴을 사용해서 sheet가 확장될때까지 다른 일을 한다 (애니메이셔 0.5초동아)
                        Button(onClick = {
                            scope.launch {
                                //축소 되어있다면 확장하고 반대라면 축소한다
                                if(sheetState.isCollapsed) {
                                    sheetState.expand()
                                }else{
                                    sheetState.collapse()
                                }
                            }
                        }) {
                            //button 클릭해서 현재 시트 상태보기 .이상태 값을 이용해서 애니메이션을 조작할 수 있다.
                            Text(text = "bottom sheet fraction: ${sheetState.progress.fraction}")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun BottomSheet() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "toggle test")
        }
    }
}