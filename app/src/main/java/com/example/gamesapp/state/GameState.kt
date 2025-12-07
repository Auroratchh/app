package com.example.gamesapp.state

import com.example.gamesapp.models.EsrbRating
import com.example.gamesapp.models.Platform

/***
 * Project: Games App
 * Package: com.example.gamesapp.state
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:45
 * All rights reserved 2025.
 **/
data class GameState(
    val id: Long = 0,
    val slug: String = "",
    val name: String = "",
    val description: String = "",
    val released: String = "",
    val tba: Boolean = false,
    val website: String = "",
    val backgroundImage: String = "",
    val rating: Float = 0f,
    val ratingsCount: Long = 0,
    val reviewsTextCount: String = "",
    val added: Long = 0,
    val metacritic: Long = 0,
    val playtime: Long = 0,
    val suggestionsCount: Long = 0,
    val updated: String = "",
    val esrbRating: EsrbRating = EsrbRating(0,"",""),
    val platforms: List<Platform> = emptyList()
)
