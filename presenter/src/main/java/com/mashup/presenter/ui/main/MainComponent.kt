package com.mashup.presenter.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

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
