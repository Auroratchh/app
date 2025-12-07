package com.example.ghibliapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_films")
data class Film(
    @PrimaryKey
    val id: String,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_title_romanised")
    val originalTitleRomanised: String,
    val image: String,
    @SerializedName("movie_banner")
    val movieBanner: String,
    val description: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("running_time")
    val runningTime: String,
    @SerializedName("rt_score")
    val rtScore: String,
    var isFavorite: Boolean = false
)