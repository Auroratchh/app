package com.example.ghibliapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ghibliapp.models.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}