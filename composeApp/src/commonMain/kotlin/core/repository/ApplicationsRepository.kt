package core.repository

import androidx.compose.runtime.remember
import androidx.room.RoomDatabase
import core.db.data.Application
import data.database.Kmp_database

class ApplicationsRepository(database: Kmp_database) {
    private val applicationDao = database.applicationDao()
    /**
     * Inserts an applciation in the Room database
     */
    suspend fun insertApplication(app : Application){
        applicationDao.insertApplication(app)
    }
}