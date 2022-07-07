package com.mashup.presenter.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: MainNavScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: MainNavScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Setting: MainNavScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )

    companion object {
        fun fromRoute(route: String?): MainNavScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Profile.route -> Profile
                Setting.route -> Setting
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
