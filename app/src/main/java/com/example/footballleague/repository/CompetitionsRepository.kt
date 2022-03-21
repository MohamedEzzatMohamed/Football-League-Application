package com.example.footballleague.repository

import com.example.footballleague.model.Competition
import com.example.footballleague.model.CompetitionsListResponse
import com.example.footballleague.model.TeamsListResponse
import retrofit2.Response

internal interface CompetitionsRepository {

//    suspend fun getCompetitionDetails(
//        competitionId: Int
//    ): Response<Competition>? = null

    suspend fun getTeamsList(
        competitionId: Int
    ): Response<TeamsListResponse>? = null

    suspend fun getCompetitionsList(
    ): Response<CompetitionsListResponse>? = null

}