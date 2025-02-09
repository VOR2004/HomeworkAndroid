package ru.ucheba.hw1.database.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.ucheba.hw1.database.dao.MovieDao
import ru.ucheba.hw1.database.entities.MovieEntity

class MovieRepository(
    private val movieDao: MovieDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun saveMovie(movieEntity: MovieEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            movieDao.saveMovie(movieEntity)
        }
    }

    fun getMoviesForUser(userId: String): Flow<List<MovieEntity>> {
        return movieDao.getMoviesForUser(userId)
    }

    suspend fun deleteMovie(id: String) {
        movieDao.deleteMovieById(id)
    }
}