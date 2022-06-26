package com.mashup.presenter.dev

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun BridgeList(items: List<Class<out Activity>>) {
    LazyColumn {
        items(items.size) { index ->
            BridgeItem(items[index])
        }
    }
}

@Composable
fun BridgeItem(activity: Class<out Activity>) {
    val context = LocalContext.current

    Button(
        onClick = { context.startActivity(Intent(context, activity)) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = activity.simpleName)
    }
}
