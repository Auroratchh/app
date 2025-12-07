package com.example.gamesapp.models

/***
 * Project: Games App
 * Package: com.example.gamesapp.models
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:45
 * All rights reserved 2025.
 **/
data class GamesModel(
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<GameModel>
)
