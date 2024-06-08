import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getKmp_database

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getKmp_database()
    }
    App(database)
}