package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import core.db.data.Application
import core.db.data.dao.ApplicationsDao
import data.Note
import data.NoteDao

@Database(entities = [Application::class, Note::class], version = 7)

abstract class Kmp_database : RoomDatabase() {
    abstract fun applicationDao(): ApplicationsDao
    abstract fun noteDao(): NoteDao

}