package database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.Kmp_database
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<Kmp_database> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "kmp_database.db")
    return Room.databaseBuilder<Kmp_database>(
        name = dbFile.absolutePath,
    ) .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}