package core.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "applications")
data class Application(
                        @PrimaryKey(autoGenerate = true)
                        @ColumnInfo(name = "id")
                        var id : Long =0,
                        @ColumnInfo(name = "nom")
                        val nom : String,
                        @ColumnInfo(name = "description")
                        val description :String) {
}