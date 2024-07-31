package core

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.room.RoomDatabase
import data.database.Kmp_database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Core (){
    companion object{
        val LocalDatabase = staticCompositionLocalOf<Kmp_database> {
            error("Database not provided")
        }
    }
}
val LocalDatabase = staticCompositionLocalOf<Kmp_database> {
    error("Database not provided")
}