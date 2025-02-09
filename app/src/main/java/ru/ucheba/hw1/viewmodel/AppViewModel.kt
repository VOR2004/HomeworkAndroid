package ru.ucheba.hw1.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.ucheba.hw1.database.DataBase
import ru.ucheba.hw1.database.entities.MovieEntity
import ru.ucheba.hw1.database.entities.UserEntity
import ru.ucheba.hw1.database.repository.MovieRepository
import ru.ucheba.hw1.database.repository.UserRepository
import ru.ucheba.hw1.database.session.SessionManager
import ru.ucheba.hw1.utils.AuthUtils

class AppViewModel(application: Application) : ViewModel() {

    private val repository: UserRepository
    private val movieRepository: MovieRepository
    private val sessionManager = SessionManager(application)

    init {
        val userDb = DataBase.getInstance(application)
        val userDao = userDb.userDao()
        val movieDao = userDb.movieDao()
        movieRepository = MovieRepository(movieDao)
        repository = UserRepository(userDao)
    }

    fun isLoggedIn(): Boolean =
        sessionManager.isLoggedIn()

    fun saveUser(id: String, userName: String, email: String, password: String) {
        repository.saveUser(
            UserEntity(id, userName, email, AuthUtils.hashPasswordPBKDF2(password))
        )
    }

    fun saveLoginSession(id: String) {
        viewModelScope.launch {
            sessionManager.setLoggedIn(true)
            sessionManager.setId(id)
        }
    }

    suspend fun getId(boolean: Boolean, value: String): String =
        repository.getId(boolean, value)

    fun logout() {
        viewModelScope.launch {
            sessionManager.clearSession()
        }
    }

    suspend fun checkEmail(email: String): Boolean =
        repository.findByEmail(email)

    suspend fun getHashPassword(b: Boolean, value: String): String? =
        repository.getPasswordHash(b, value)

    fun addMovie(
        id: String,
        userId: String,
        movieName: String,
        description: String?,
        year: String,
        rating: Float,
        uri: String?
    ) {
        movieRepository.saveMovie(
            MovieEntity(id, userId, movieName, description, year, rating, uri)
        )
    }


    private val _movies =
        MutableStateFlow<List<MovieEntity>>(emptyList())

    val movies: StateFlow<List<MovieEntity>> =
        _movies.asStateFlow()

    fun getMoviesForUser(userId: String) {
        viewModelScope.launch {
            movieRepository.getMoviesForUser(userId).collect {
                _movies.value = it
            }
        }
    }

    fun deleteMovie(movie: MovieEntity) {
        val currentList = _movies.value.toMutableList()
        val index = currentList.indexOf(movie)

        if (index == -1) return
        currentList.removeAt(index)
        _movies.value = currentList.toList()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieRepository.deleteMovie(movie.id)
            } catch (e: SQLiteException) {
                withContext(Dispatchers.Main) {
                    val newList = _movies.value.toMutableList()
                    newList.add(index, movie)
                    _movies.value = newList.toList()
                }
            }
        }
    }

    fun getId(): String? = sessionManager.getId()
}