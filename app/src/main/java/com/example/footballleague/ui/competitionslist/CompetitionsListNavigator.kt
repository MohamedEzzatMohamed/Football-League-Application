package com.example.footballleague.ui.competitionslist

import com.example.footballleague.model.Competition

interface CompetitionsNavigator {
    fun showCompetitionDetails(competition: Competition)
}