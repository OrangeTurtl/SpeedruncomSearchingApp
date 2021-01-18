package com.stepanf.speedruncomapiapp.ui.games_search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.stepanf.speedruncomapiapp.data.games.SpeedrunGamesRepository


class GamesViewModel @ViewModelInject constructor(
    private val gamesRepository: SpeedrunGamesRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_GAME_NAME, DEFAULT_GAME_NAME)

    private val currentGameName = MutableLiveData(DEFAULT_GAME_NAME)

    val games = currentGameName.switchMap { name ->
        gamesRepository.getGamesSearchResults(name).cachedIn(viewModelScope)
    }

    fun searchPhotos(gameName: String) {
        currentGameName.value = gameName
    }

    companion object {
        private const val CURRENT_GAME_NAME = "current_game_name"
        private const val DEFAULT_GAME_NAME = "game"
    }
}