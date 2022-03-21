package com.example.footballleague.model

data class Competition(
    val area: Area? = null,
    val code: String? = "",
    val currentSeason: CurrentSeason? = null,
    val emblemUrl: String? = "",
    val id: Int,
    val lastUpdated: String? = "",
    val name: String,
    val numberOfAvailableSeasons: Int? = 0,
    val plan: String? = ""
)