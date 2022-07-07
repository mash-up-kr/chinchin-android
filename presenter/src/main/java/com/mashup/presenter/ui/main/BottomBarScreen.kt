package com.mashup.presenter.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: BottomBarScreen(
        route = "Profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Setting: BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )

    companion object {
        fun fromRoute(route: String?): BottomBarScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Profile.route -> Profile
                Setting.route -> Setting
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
