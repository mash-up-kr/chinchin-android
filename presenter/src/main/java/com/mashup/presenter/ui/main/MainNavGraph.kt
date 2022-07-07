package com.mashup.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.ui.main.home.HomeScreen
import com.mashup.presenter.ui.main.profile.ProfileScreen
import com.mashup.presenter.ui.main.setting.SettingScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = MainNavScreen.Setting.route) {
            SettingScreen()
        }
    }
}
