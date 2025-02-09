package ru.ucheba.hw1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movies",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "movie_name")
    val movieName: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "uri")
    val uri: String?
)