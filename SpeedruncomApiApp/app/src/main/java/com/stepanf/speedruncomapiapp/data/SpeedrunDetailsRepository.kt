package com.stepanf.speedruncomapiapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stepanf.speedruncomapiapp.api.SpeedrunDetailsApi
import com.stepanf.speedruncomapiapp.api.responces.SpeedrunDeveloperResponse
import com.stepanf.speedruncomapiapp.api.responces.SpeedrunGenreResponse
import com.stepanf.speedruncomapiapp.api.responces.SpeedrunPlatformResponse
import com.stepanf.speedruncomapiapp.api.responces.SpeedrunRegionResponse
import com.stepanf.speedruncomapiapp.data.details.SpeedrunDeveloper
import com.stepanf.speedruncomapiapp.data.details.SpeedrunGenre
import com.stepanf.speedruncomapiapp.data.details.SpeedrunPlatform
import com.stepanf.speedruncomapiapp.data.details.SpeedrunRegion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
private const val TAG = "SpeedrunDetailsRepo"

@Singleton
class SpeedrunDetailsRepository @Inject constructor(private val speedrunDetailsApi: SpeedrunDetailsApi) {
    var platform = MutableLiveData<SpeedrunPlatform>()
    var genre = MutableLiveData<SpeedrunGenre>()
    var developer = MutableLiveData<SpeedrunDeveloper>()
    var region = MutableLiveData<SpeedrunRegion>()

    fun callPlatform(id: String) {
        speedrunDetailsApi.getPlatform(id).enqueue(object : Callback<SpeedrunPlatformResponse> {
            override fun onResponse(
                call: Call<SpeedrunPlatformResponse>,
                response: Response<SpeedrunPlatformResponse>
            ) {
                platform.value = response.body()!!.platform
            }

            override fun onFailure(call: Call<SpeedrunPlatformResponse>, t: Throwable) {
                Log.d(TAG, "PlatformResponse onFailure: ${t.message}")
            }
        })
    }

    fun callGenre(id: String) {
        speedrunDetailsApi.getGenre(id).enqueue(object : Callback<SpeedrunGenreResponse> {
            override fun onResponse(
                call: Call<SpeedrunGenreResponse>,
                response: Response<SpeedrunGenreResponse>
            ) {
                genre.value = response.body()!!.genre
            }

            override fun onFailure(call: Call<SpeedrunGenreResponse>, t: Throwable) {
                Log.d(TAG, "GenreResponse onFailure: ${t.message}")
            }
        })
    }

    fun callRegion(id: String) {
        speedrunDetailsApi.getRegion(id).enqueue(object : Callback<SpeedrunRegionResponse> {
            override fun onResponse(
                call: Call<SpeedrunRegionResponse>,
                response: Response<SpeedrunRegionResponse>
            ) {
                region.value = response.body()!!.region
            }

            override fun onFailure(call: Call<SpeedrunRegionResponse>, t: Throwable) {
                Log.d(TAG, "RegionResponse onFailure: ${t.message}")
            }
        })
    }

    fun callDeveloper(id: String) {
        speedrunDetailsApi.getDeveloper(id).enqueue(object : Callback<SpeedrunDeveloperResponse> {
            override fun onResponse(
                call: Call<SpeedrunDeveloperResponse>,
                response: Response<SpeedrunDeveloperResponse>
            ) {
                developer.value = response.body()!!.developer
            }

            override fun onFailure(call: Call<SpeedrunDeveloperResponse>, t: Throwable) {
                Log.d(TAG, "DeveloperResponse onFailure: ${t.message}")
            }
        })
    }

    fun clearDetails() {
        platform = MutableLiveData<SpeedrunPlatform>()
        genre = MutableLiveData<SpeedrunGenre>()
        region = MutableLiveData<SpeedrunRegion>()
        developer = MutableLiveData<SpeedrunDeveloper>()
    }
}
