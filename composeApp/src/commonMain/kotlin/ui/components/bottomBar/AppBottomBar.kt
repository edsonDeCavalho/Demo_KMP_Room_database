package ui.components.bottomBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ui.dataUi.BottomBarScreen

/**
 * Composant BottomBar
 */
@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Tasks,
        BottomBarScreen.Options,
        BottomBarScreen.ApiDemo
    )
    BottomNavigation(backgroundColor= Color.Blue) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController
            )
        }
    }
}

/**
 * Cellule de bare de navigation inferieur.
 */
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigationItem(
        label = {
            Text(text = screen.label, color = Color.White)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.route + "icon",
                tint = Color.White
            )
        },
        selected = screen.route == backStackEntry.value?.destination?.route,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}