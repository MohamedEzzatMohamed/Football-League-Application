package com.example.footballleague.network

import com.example.footballleague.model.Competition
import com.example.footballleague.model.CompetitionsListResponse
import com.example.footballleague.model.TeamsListResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit interface to implement the api functions
 */
interface ApiInterface {

    /**
     * get function get list of competitions
     * params(api_key)
     * response: list of competitions
     */
    @GET("/v2/competitions")
    suspend fun getAllCompetitions(
    ): Response<CompetitionsListResponse>

    /**
     * get function competition details
     * params(competition_id, api_key)
     * response selected competition data
     */
    @GET("/v2/competitions/{competition_id}/teams")
    suspend fun getCompetitionDetails(
        @Path("competition_id") competitionId: Int,
        @Query("api_key") apiKey: String
    ): Response<Competition>

    /**
     * get function to get list of teams in a specific competition
     * params(competition_id, api_key)
     * response list of teams with main data about each team
     */
    @GET("/v2/competitions/{competition_id}/teams")
    suspend fun getTeamsList(
        @Path("competition_id") competitionId: Int,
        @Query("X-Auth-Token") apiKey: String
    ): Response<TeamsListResponse>
}