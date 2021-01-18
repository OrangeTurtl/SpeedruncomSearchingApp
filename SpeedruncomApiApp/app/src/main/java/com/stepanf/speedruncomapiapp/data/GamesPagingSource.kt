package com.stepanf.speedruncomapiapp.data

import androidx.paging.PagingSource
import com.stepanf.speedruncomapiapp.api.SpeedrunGamesApi
import com.stepanf.speedruncomapiapp.data.games.SpeedrunGame
import retrofit2.HttpException
import java.io.IOException

private const val SPEEDRUN_GAMES_STARTING_PAGE_INDEX = 0
private const val TAG = "GamesPagingSource"

class GamesPagingSource(
    private val speedrunGamesApi: SpeedrunGamesApi,
    private val gameName: String,
) : PagingSource<Int, SpeedrunGame>() {

    companion object {
        const val PER_PAGE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpeedrunGame> {
        val position = params.key ?: SPEEDRUN_GAMES_STARTING_PAGE_INDEX
        val offset = position * PER_PAGE
        return try {
            val responce = speedrunGamesApi.searchGames(name = gameName, max = params.loadSize, offset = offset)
            val games = responce.data
            LoadResult.Page(
                    data = games,
                    prevKey = if(position == SPEEDRUN_GAMES_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if(games.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}