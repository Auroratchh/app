package com.example.ghibliapp.data.remote

import com.example.ghibliapp.models.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGhibli {
    @GET("films")
    suspend fun getFilms(): Response<List<Film>>

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") id: String): Response<Film>
}