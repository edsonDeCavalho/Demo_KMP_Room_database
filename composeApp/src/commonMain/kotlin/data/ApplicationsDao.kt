package core.db.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import core.db.data.Application
import kotlinx.coroutines.flow.Flow

/**
 * Dao pour la class applications
 */
@Dao
interface ApplicationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(application: Application): Long

    @Update
    suspend fun updateApplication(application: Application)

    @Delete
    suspend fun deleteApplication(application: Application)

    @Query("SELECT * FROM applications WHERE id = :id")
    suspend fun getApplicationById(id: Long): Application?

    @Query("SELECT * FROM applications")
    fun getAllApplications(): Flow<List<Application>>
}