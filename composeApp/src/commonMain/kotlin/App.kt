import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.RoomDatabase
import data.database.Kmp_database
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import core.LocalDatabase
import ui.pages.mainScreen.MainScreen
/**
 * App entry
 */
@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<Kmp_database>) {
    val database = remember { databaseBuilder.build() }
    val noteDao = database.noteDao()
    val scope = rememberCoroutineScope()
    MaterialTheme {
        /**
         * Main page
         */
        CompositionLocalProvider(LocalDatabase provides database) {
            MainScreen(database)
        }
    }
}

