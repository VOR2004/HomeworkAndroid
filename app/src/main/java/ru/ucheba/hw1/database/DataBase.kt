package ru.ucheba.hw1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.ucheba.hw1.database.dao.MovieDao
import ru.ucheba.hw1.database.dao.UserDao
import ru.ucheba.hw1.database.entities.MovieEntity
import ru.ucheba.hw1.database.entities.UserEntity
import ru.ucheba.hw1.key.KeyNames

@Database(
    entities = [UserEntity::class, MovieEntity::class],
    version = 6,
)

abstract class DataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        KeyNames.DB_NAME
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}