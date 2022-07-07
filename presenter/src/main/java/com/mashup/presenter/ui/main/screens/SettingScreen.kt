package com.mashup.presenter.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SettingScreen(){
    ConstraintLayout(modifier = Modifier.background(Color.Green)){

        val text = createRef()
        Text("Home", Modifier.constrainAs(text){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}
