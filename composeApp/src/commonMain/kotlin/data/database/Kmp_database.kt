package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import core.db.data.Application
import core.db.data.dao.ApplicationsDao

@Database(entities = [Application::class], version = 6)

abstract class Kmp_database : RoomDatabase() {
    abstract fun applicationDao(): ApplicationsDao
    
}