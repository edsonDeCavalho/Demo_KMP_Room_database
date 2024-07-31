package ui.dataUi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Routes BottomBar
 */
sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route="home",
        label="Home",
        icon= Icons.Rounded.Home
    )
    object Tasks: BottomBarScreen(
        route="room",
        label="Room",
        icon= Icons.Rounded.Android
    )
    object Options: BottomBarScreen(
        route="options",
        label="Options",
        icon= Icons.Rounded.Settings
    )
    object NoteDetails: BottomBarScreen(
        route="notedetails",
        label="Note details",
        icon= Icons.Rounded.Settings
    )
}

/**
 * Routes TopBar
 */

sealed class TopBarScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object InfoPage: BottomBarScreen(
        route="info",
        label="Info",
        icon= Icons.Rounded.Info
    )
}
