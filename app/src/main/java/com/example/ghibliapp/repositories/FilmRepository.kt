package com.example.ghibliapp.repositories

import com.example.ghibliapp.data.local.FilmDao
import com.example.ghibliapp.data.remote.ApiGhibli
import com.example.ghibliapp.models.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val apiGhibli: ApiGhibli,
    private val filmDao: FilmDao
) {
    suspend fun getFilms(): List<Film>? {
        val response = apiGhibli.getFilms()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getFilmById(id: String): Film? {
        val response = apiGhibli.getFilmById(id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    fun getAllFavorites(): Flow<List<Film>> {
        return filmDao.getAllFavorites()
    }

    suspend fun addFavorite(film: Film) {
        filmDao.insertFavorite(film.copy(isFavorite = true))
    }

    suspend fun removeFavorite(film: Film) {
        filmDao.deleteFavorite(film)
    }

    suspend fun removeFavoriteById(filmId: String) {
        filmDao.deleteFavoriteById(filmId)
    }

    suspend fun isFavorite(filmId: String): Boolean {
        return filmDao.isFavorite(filmId)
    }
}