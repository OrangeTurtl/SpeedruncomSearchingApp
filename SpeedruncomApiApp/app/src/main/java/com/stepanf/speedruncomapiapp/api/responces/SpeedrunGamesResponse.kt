package com.stepanf.speedruncomapiapp.api.responces

import com.stepanf.speedruncomapiapp.data.games.SpeedrunGame

data class SpeedrunGamesResponse (
    val data: List<SpeedrunGame>
)