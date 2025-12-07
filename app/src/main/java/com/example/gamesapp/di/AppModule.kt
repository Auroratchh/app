package com.example.gamesapp.di

import com.example.gamesapp.data.ApiGames
import com.example.gamesapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
 * Project: Games App
 * Package: com.example.gamesapp.di
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:00
 * All rights reserved 2025.
 **/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiGames(retrofit: Retrofit): ApiGames {
        return retrofit.create(ApiGames::class.java)
    }
}