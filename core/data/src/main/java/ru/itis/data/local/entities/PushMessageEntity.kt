package ru.itis.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.itis.domain.model.PushMessage
import ru.itis.utils.enums.PushCategory

@Entity(tableName = "push_messages")
data class PushMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val data: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun toDomain() = PushMessage(
        id = id,
        category = PushCategory.valueOf(category),
        data = data,
        timestamp = timestamp
    )
}