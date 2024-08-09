package data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Classe representant une note
 */
@Entity(tableName = "notes")
@Serializable
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long =0,
    @ColumnInfo(name = "title")
    val title : String= "",
    @ColumnInfo(name = "text")
    val text :String ="",
    @ColumnInfo(name = "image")
    val image :String ="") {
}