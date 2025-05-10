package ru.itis.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.data.local.database.RoomDatabase
import ru.itis.data.local.dao.CityWeatherCacheDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSearchDatabase(
        @ApplicationContext context: Context
    ): RoomDatabase {
        return Room.databaseBuilder(
            context,
            RoomDatabase::class.java,
            "search_database"
        ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun provideCityWeatherCacheDao(db: RoomDatabase): CityWeatherCacheDao =
        db.cityWeatherCacheDao()
}