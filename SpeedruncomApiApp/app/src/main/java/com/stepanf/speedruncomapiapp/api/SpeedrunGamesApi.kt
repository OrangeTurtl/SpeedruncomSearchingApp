package com.stepanf.speedruncomapiapp.api

import com.stepanf.speedruncomapiapp.api.responces.SpeedrunGamesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SpeedrunGamesApi{

    @Headers("User-Agent: SpeedrunComApiApp/0.1")
    @GET("api/v1/games")
    suspend fun searchGames (
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("max") max: Int,
        @Query("orderby") orderby: String = "created" ,
        @Query("direction") direction: String = "desc"
    ) : SpeedrunGamesResponse
}