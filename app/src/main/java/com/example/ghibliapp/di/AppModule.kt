package com.example.ghibliapp.di

import android.content.Context
import androidx.room.Room
import com.example.ghibliapp.data.local.FilmDao
import com.example.ghibliapp.data.local.FilmDatabase
import com.example.ghibliapp.data.remote.ApiGhibli
import com.example.ghibliapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    fun providesApiGhibli(retrofit: Retrofit): ApiGhibli {
        return retrofit.create(ApiGhibli::class.java)
    }

    @Singleton
    @Provides
    fun provideFilmDatabase(@ApplicationContext context: Context): FilmDatabase {
        return Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            "film_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFilmDao(database: FilmDatabase): FilmDao {
        return database.filmDao()
    }
}