package database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.Kmp_database
import data.database.instantiateImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

fun getKmp_database(): RoomDatabase.Builder<Kmp_database> {
    val dbFile = NSHomeDirectory() + "/kmp_database.db"
    return Room.databaseBuilder<Kmp_database>(
        name = dbFile,
        factory = { Kmp_database::class.instantiateImpl() }
    ).fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}