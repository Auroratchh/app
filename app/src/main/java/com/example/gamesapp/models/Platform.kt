package com.example.gamesapp.models

import com.google.gson.annotations.SerializedName

/***
 * Project: Games App
 * Package: com.example.gamesapp.models
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:42
 * All rights reserved 2025.
 **/
data class Platform(
    val platform: EsrbRating,
    @SerializedName("released_at")
    val releasedAt: String,
    val requirements: Requirements
)
