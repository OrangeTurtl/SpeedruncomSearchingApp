package com.stepanf.speedruncomapiapp.data.games

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.stepanf.speedruncomapiapp.api.SpeedrunGamesApi
import com.stepanf.speedruncomapiapp.data.GamesPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeedrunGamesRepository @Inject constructor(private val speedrunGamesApi: SpeedrunGamesApi) {
    fun getGamesSearchResults(gameName: String) =
        Pager(
            config = PagingConfig(
                pageSize = GamesPagingSource.PER_PAGE,
                maxSize = 100,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { GamesPagingSource(speedrunGamesApi, gameName) }
        ).liveData
}