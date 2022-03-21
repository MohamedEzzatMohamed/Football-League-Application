package com.example.footballleague.repository

import android.util.Log
import com.example.footballleague.BuildConfig
import com.example.footballleague.model.Competition
import com.example.footballleague.model.CompetitionsListResponse
import com.example.footballleague.model.TeamsListResponse
import com.example.footballleague.network.RetrofitClass
import retrofit2.Response

internal class CompetitionsRepositoryImplementer : CompetitionsRepository {

//    override suspend fun getCompetitionDetails(competitionId: Int):
//            Response<Competition> {
//        return RetrofitClass.apiInterface.getCompetitionDetails(competitionId, BuildConfig.API_KEY)
//    }

    override suspend fun getCompetitionsList(): Response<CompetitionsListResponse> {
        return RetrofitClass.apiInterface.getAllCompetitions()
    }

    override suspend fun getTeamsList(competitionId: Int): Response<TeamsListResponse> {
        return RetrofitClass.apiInterface.getTeamsList(competitionId, BuildConfig.API_KEY)
    }

}