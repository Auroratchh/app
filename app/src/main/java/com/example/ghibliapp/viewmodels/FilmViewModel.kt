package com.example.ghibliapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghibliapp.models.Film
import com.example.ghibliapp.repositories.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val repository: FilmRepository
): ViewModel() {

    private val _films = MutableStateFlow<List<Film>>(emptyList())
    val films: StateFlow<List<Film>> = _films

    private val _searchResults = MutableStateFlow<List<Film>>(emptyList())
    val searchResults: StateFlow<List<Film>> = _searchResults

    private val _favorites = MutableStateFlow<List<Film>>(emptyList())
    val favorites: StateFlow<List<Film>> = _favorites

    var selectedFilm by mutableStateOf<Film?>(null)
        private set

    var isLoading = MutableStateFlow(false)
        private set

    var isDetailLoading = MutableStateFlow(false)
        private set

    init {
        loadFilms()
        loadFavorites()
    }

    fun loadFilms() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                val result = repository.getFilms()
                _films.value = result ?: emptyList()
                isLoading.value = false
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            repository.getAllFavorites().collect { favoritesList ->
                _favorites.value = favoritesList
            }
        }
    }

    fun searchFilms(query: String) {
        if (query.isEmpty()) {
            _searchResults.value = emptyList()
            return
        }

        val filtered = _films.value.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.director.contains(query, ignoreCase = true) ||
                    it.releaseDate.contains(query, ignoreCase = true)
        }
        _searchResults.value = filtered
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
    }

    fun getFilmById(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isDetailLoading.value = true
                val result = repository.getFilmById(id)
                if (result != null) {
                    val isFav = repository.isFavorite(id)
                    selectedFilm = result.copy(isFavorite = isFav)
                }
                isDetailLoading.value = false
            }
        }
    }

    fun toggleFavorite(film: Film) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (film.isFavorite) {
                    repository.removeFavorite(film)
                    selectedFilm = selectedFilm?.copy(isFavorite = false)
                } else {
                    repository.addFavorite(film)
                    selectedFilm = selectedFilm?.copy(isFavorite = true)
                }
            }
        }
    }

    fun removeFavorite(filmId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.removeFavoriteById(filmId)
            }
        }
    }

    suspend fun checkIfFavorite(filmId: String): Boolean {
        return withContext(Dispatchers.IO) {
            repository.isFavorite(filmId)
        }
    }
}