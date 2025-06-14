package ru.itis.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.itis.data.local.entities.PushMessageEntity

@Dao
interface PushMessageDao {
    @Insert
    suspend fun insert(entity: PushMessageEntity): Long

    @Query("SELECT * FROM push_messages ORDER BY timestamp DESC")
    fun getAll(): Flow<List<PushMessageEntity>>
}