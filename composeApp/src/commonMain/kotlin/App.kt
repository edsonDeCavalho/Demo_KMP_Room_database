import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.RoomDatabase
import core.LocalDatabase
import data.Note
import data.database.Kmp_database
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
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
    val notestToInsert = listOf(
        Note(title="NetPlus", text="HelloV1",image =""),
        Note(title="WeatherPro", text="ForecastV1",image =""),
        Note(title="MusicStream", text="TuneIn",image =""),
    )
    notestToInsert.forEach {
        scope.launch {
            noteDao.insertNote(it)
        }
    }
    MaterialTheme{
        /**
         * Main page
         */
       // MainScreen(database)
        CompositionLocalProvider(LocalDatabase provides database) {
            MainScreen(database)
        }
    }
}

