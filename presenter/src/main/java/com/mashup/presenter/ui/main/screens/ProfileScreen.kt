package com.mashup.presenter.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.presenter.ui.main.BottomBarScreen

@Composable
fun ProfileScreen(){
    ConstraintLayout(modifier = Modifier.background(Color.Cyan)){

        val text = createRef()
        Text("Profile", Modifier.constrainAs(text){
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}