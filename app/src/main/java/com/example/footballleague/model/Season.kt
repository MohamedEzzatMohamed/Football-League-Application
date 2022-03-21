package com.example.footballleague.model

data class Season(
    val availableStages: ArrayList<String>? = ArrayList(),
    val currentMatchday: Int? = 0,
    val endDate: String? = "",
    val id: Int? = 0,
    val startDate: String? = ""
)