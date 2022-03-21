package com.example.footballleague.ui.competitiondetails

import com.example.footballleague.model.Team

interface CompetitionDetailsNavigator {
    fun showTeamDetails(team: Team)
}