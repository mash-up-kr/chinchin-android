package com.mashup.presenter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class SettingFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SettingContent()
            }
        }
    }
}

@Preview
@Composable
fun SettingContent(){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "This is Setting Fragment",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}