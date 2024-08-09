package ui.navigation

import MainViewModel
import RoomDemoPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import data.database.Kmp_database
import ui.dataUi.BottomBarScreen
import ui.dataUi.TopBarScreen
import ui.pages.apidemo.ApiDemoPage
import ui.pages.cali.InfoPage
import ui.pages.createNote.CreateNotePage
import ui.pages.home.HomeScreen
import ui.pages.notedetails.NoteDetails
import ui.pages.options.Options
import ui.pages.permissions.PermissionsPage


@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingModifier: Modifier,
    database : Kmp_database,
    mainViewModel: MainViewModel
) {

    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        /// paddingModifier.background(Color.Cyan)
        /**
         * Bottom Bar routes
         */
        //Page Home
        composable(route= BottomBarScreen.Home.route) {
            HomeScreen(paddingModifier,navController = navController,mainViewModel=mainViewModel)
        }
        //Page liste de Notes
        composable(route= BottomBarScreen.Tasks.route) {
            RoomDemoPage(
                database = database,
                paddingModifier = paddingModifier,
                navController = navController,
                mainViewModel=mainViewModel)
        }
        //Page Options
        composable(route= BottomBarScreen.Options.route) {
            Options(paddingModifier,mainViewModel)
        }
        //Page create Note
        composable(route= BottomBarScreen.CreateNote.route) {
            CreateNotePage(paddingModifier,mainViewModel)
        }
        //Page détails note
        composable(
            route = "${BottomBarScreen.NoteDetails.route}/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId")
            if (noteId != null) {
                NoteDetails(noteId,paddingModifier,mainViewModel)
            }
        }
        //Page permissions
        composable(route = BottomBarScreen.Permissions.route) {
            PermissionsPage()
        }
        //Page demo api
        composable(route= BottomBarScreen.ApiDemo.route) {
            ApiDemoPage(paddingModifier,navController,mainViewModel)
        }
        /**
         * Top bar
         */
        //Page Infos
        composable(route= TopBarScreen.InfoPage.route) {
            InfoPage(paddingModifier,mainViewModel)
        }

    }
}