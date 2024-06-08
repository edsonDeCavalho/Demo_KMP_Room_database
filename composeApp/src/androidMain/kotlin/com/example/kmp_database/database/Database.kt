package com.example.kmp_database.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.Kmp_database
import kotlinx.coroutines.Dispatchers

fun getKmp_database(context: Context): RoomDatabase.Builder<Kmp_database> {
    val dbFile = context.getDatabasePath("kmp_database.db")
    return Room.databaseBuilder<Kmp_database>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    ).fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}