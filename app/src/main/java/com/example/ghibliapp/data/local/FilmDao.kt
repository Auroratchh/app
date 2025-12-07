package com.example.ghibliapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ghibliapp.models.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM favorite_films ORDER BY title ASC")
    fun getAllFavorites(): Flow<List<Film>>

    @Query("SELECT * FROM favorite_films WHERE id = :filmId")
    suspend fun getFavoriteById(filmId: String): Film?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(film: Film)

    @Delete
    suspend fun deleteFavorite(film: Film)

    @Query("DELETE FROM favorite_films WHERE id = :filmId")
    suspend fun deleteFavoriteById(filmId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_films WHERE id = :filmId)")
    suspend fun isFavorite(filmId: String): Boolean
}