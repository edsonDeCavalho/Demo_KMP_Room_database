package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.Note
import data.NoteDao

/**
 * Classe database Room
 */
@Database(entities = [Note::class], version = 8)

abstract class Kmp_database : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}