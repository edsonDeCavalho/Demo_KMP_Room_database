import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.room.RoomDatabase
import data.database.Kmp_database
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.data.BottomBarScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ui.components.topBar.CustomTopBar
import ui.data.TopBarScreen
import ui.pages.Info.InfoPage

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<Kmp_database>) {
    val database = remember { databaseBuilder.build() }
    MaterialTheme{
        MainScreen(database)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(database : Kmp_database) {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    Scaffold(
        topBar = { CustomTopBar(mainViewModel,navController) },
        bottomBar = { AppBottomBar(navController = navController) },
    )
    {paddingValues->
        BottomNavigationGraph(
            navController = navController,
            paddingModifier = Modifier.padding(paddingValues) ,
            database = database,
            mainViewModel = mainViewModel
        )
    }
}


@Composable
fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Tasks,
        BottomBarScreen.Options
    )
    BottomNavigation(backgroundColor=Color.Blue) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController
            )
        }
    }
}

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
@Composable
fun HomeScreen(paddingModifier: Modifier) {
    Column(
        modifier = paddingModifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "home", fontSize = 100.sp)
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(onClick = { }) {
            Text("Room")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(onClick = { }) {
            Text("API")
        }
    }
}

@Composable
fun Options(paddingModifier: Modifier) {
    Box(
        modifier = paddingModifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(text="Options", fontSize = 100.sp , modifier = Modifier.align(Alignment.Center))
    }
}

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
        composable(route= BottomBarScreen.Home.route) {
            HomeScreen(paddingModifier)
        }
        composable(route= BottomBarScreen.Tasks.route) {
            ApplicationList(
                database = database,
                paddingModifier = paddingModifier,
                navController = navController)
        }
        composable(route= BottomBarScreen.Options.route) {
            Options(paddingModifier)
        }
        /**
         * Top nav routes
         */
        composable(route= TopBarScreen.InfoPage.route) {
            InfoPage(paddingModifier,mainViewModel)
        }
        //TopBarScreen
    }
}