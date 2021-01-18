package com.stepanf.speedruncomapiapp.ui.game_details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.stepanf.speedruncomapiapp.data.SpeedrunDetailsRepository
import com.stepanf.speedruncomapiapp.data.details.SpeedrunDeveloper
import com.stepanf.speedruncomapiapp.data.details.SpeedrunGenre
import com.stepanf.speedruncomapiapp.data.details.SpeedrunPlatform
import com.stepanf.speedruncomapiapp.data.details.SpeedrunRegion
import com.stepanf.speedruncomapiapp.data.games.SpeedrunGame
import com.stepanf.speedruncomapiapp.ui.games_search.GamesViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "GameDetailsViewModel"

class GameDetailsViewModel @ViewModelInject constructor(
    private val detailsRepository: SpeedrunDetailsRepository
) : ViewModel() {
    private var searchGameDetails = false

    fun initialize(game: SpeedrunGame) {
        Log.d(TAG, "initialize: ${searchGameDetails}")
        if (!searchGameDetails) {
            searchGameDetails = true
            initializeSearchingPlatforms(game.platforms)
            initializeSearchingGenres(game.genres)
            initializeSearchingRegions(game.regions)
            initializeSearchingDevelopers(game.developers)
        }
    }

    private fun initializeSearchingPlatforms(platformIds: List<String>) {
        platformIds.forEach { id ->
            detailsRepository.callPlatform(id)
        }
    }

    private fun initializeSearchingGenres(genreIds: List<String>) {
        genreIds.forEach { id ->
            detailsRepository.callGenre(id)
        }
    }

    private fun initializeSearchingRegions(regionIds: List<String>) {
        regionIds.forEach { id ->
            detailsRepository.callRegion(id)
        }
    }

    private fun initializeSearchingDevelopers(developerIds: List<String>) {
        developerIds.forEach { id ->
            detailsRepository.callDeveloper(id)
        }
    }

    private val storedPlatforms: MutableList<SpeedrunPlatform> = mutableListOf()
    val platforms: LiveData<List<SpeedrunPlatform>> = detailsRepository.platform.switchMap {
        updateAndGetPlatforms(it)
    }

    private fun updateAndGetPlatforms(platform: SpeedrunPlatform): MutableLiveData<List<SpeedrunPlatform>> {
        storedPlatforms.add(platform)
        return MutableLiveData<List<SpeedrunPlatform>>(storedPlatforms)
    }

    private val storedGenres: MutableList<SpeedrunGenre> = mutableListOf()
    val genres: LiveData<List<SpeedrunGenre>> = detailsRepository.genre.switchMap {
        updateAndGetGenres(it)
    }

    private fun updateAndGetGenres(genre: SpeedrunGenre): MutableLiveData<List<SpeedrunGenre>> {
        storedGenres.add(genre)
        return MutableLiveData<List<SpeedrunGenre>>(storedGenres)
    }

    private val storedRegions: MutableList<SpeedrunRegion> = mutableListOf()
    val regions: LiveData<List<SpeedrunRegion>> = detailsRepository.region.switchMap {
        updateAndGetRegions(it)
    }

    private fun updateAndGetRegions(region: SpeedrunRegion): MutableLiveData<List<SpeedrunRegion>> {
        storedRegions.add(region)
        return MutableLiveData<List<SpeedrunRegion>>(storedRegions)
    }

    private val storedDevelopers: MutableList<SpeedrunDeveloper> = mutableListOf()
    val developers: LiveData<List<SpeedrunDeveloper>> = detailsRepository.developer.switchMap {
        updateAndGetDevelopers(it)
    }

    private fun updateAndGetDevelopers(developer: SpeedrunDeveloper): MutableLiveData<List<SpeedrunDeveloper>> {
        storedDevelopers.add(developer)
        return MutableLiveData<List<SpeedrunDeveloper>>(storedDevelopers)
    }

    override fun onCleared() {
        detailsRepository.clearDetails()
        super.onCleared()
    }
}