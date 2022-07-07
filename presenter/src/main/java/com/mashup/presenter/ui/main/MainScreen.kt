package com.mashup.presenter.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val screens = listOf(
        MainNavScreen.Home,
        MainNavScreen.Profile,
        MainNavScreen.Setting,
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            MainNavBar(screens = screens, currentDestination = currentDestination) { screen ->
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun MainNavBar(
    screens: List<MainNavScreen>,
    currentDestination: MainNavScreen,
    onTabSelected: (MainNavScreen) -> Unit,
) {
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                onTabSelected = {
                    onTabSelected(screen)
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainNavScreen,
    currentDestination: MainNavScreen,
    onTabSelected: () -> Unit,
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        selected = currentDestination == screen,
        onClick = { onTabSelected() }
    )
}
