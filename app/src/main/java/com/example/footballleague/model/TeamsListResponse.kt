package com.example.footballleague.model

data class TeamsListResponse(

    val competition: Competition? = null,
    val count: Int? = 0,
    val season: Season? = null,
    val teams: ArrayList<Team>? = ArrayList()
)