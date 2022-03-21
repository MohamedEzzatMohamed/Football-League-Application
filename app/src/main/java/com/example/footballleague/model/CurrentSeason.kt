package com.example.footballleague.model

data class CurrentSeason(
    val currentMatchday: Int? = 0,
    val endDate: String? = "",
    val id: Int? = 0,
    val startDate: String? = "",
    val winner: Winner? = null
)