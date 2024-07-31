package core

import androidx.compose.runtime.staticCompositionLocalOf
import data.NoteDao
import data.database.Kmp_database

val LocalDatabase = staticCompositionLocalOf<Kmp_database> {
    error("database error provider")
}

val noteDao = staticCompositionLocalOf<NoteDao> {
    error("database error provider")
}