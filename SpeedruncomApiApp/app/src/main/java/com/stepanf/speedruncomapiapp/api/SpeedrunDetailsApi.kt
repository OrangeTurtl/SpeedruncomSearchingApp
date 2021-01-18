package com.stepanf.speedruncomapiapp.api

import androidx.lifecycle.LiveData
import com.stepanf.speedruncomapiapp.api.responces.*
import com.stepanf.speedruncomapiapp.data.details.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface SpeedrunDetailsApi{

    companion object {
        const val BASE_URL = "https://www.speedrun.com/"
    }

    @Headers("User-Agent: SpeexdrunComApiApp/0.1")
    @GET("api/v1/platforms/{id}")
    fun getPlatform (
        @Path("id") id: String
    ) : Call<SpeedrunPlatformResponse>

    @Headers("User-Agent: SpeedrunComApiApp/0.1")
    @GET("api/v1/regions/{id}")
    fun getRegion (
        @Path("id") id: String
    ) : Call<SpeedrunRegionResponse>

    @Headers("User-Agent: SpeedrunComApiApp/0.1")
    @GET("api/v1/genres/{id}")
    fun getGenre (
        @Path("id") id: String
    ) : Call<SpeedrunGenreResponse>

    @Headers("User-Agent: SpeedrunComApiApp/0.1")
    @GET("api/v1/developers/{id}")
    fun getDeveloper (
        @Path("id") id: String
    ) : Call<SpeedrunDeveloperResponse>
}
