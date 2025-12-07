package com.example.gamesapp.models

import com.google.gson.annotations.SerializedName

/***
 * Project: Games App
 * Package: com.example.gamesapp.models
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:44
 * All rights reserved 2025.
 **/
data class GameModel(
    val id: Long,
    val slug: String,
    val name: String,
    val description: String,
    val released: String,
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String,
    val website: String,
    val rating: Float,
    @SerializedName("ratings_count")
    val ratingsCount: Long,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: String,
    val added: Long,
    val metacritic: Long,
    val playtime: Long,
    @SerializedName("suggestions_count")
    val suggestionsCount: Long,
    val updated: String,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating,
    val platforms: List<Platform>
)
