package com.stepanf.speedruncomapiapp.api.responces

import com.google.gson.annotations.SerializedName
import com.stepanf.speedruncomapiapp.data.details.SpeedrunDeveloper
import com.stepanf.speedruncomapiapp.data.details.SpeedrunGenre
import com.stepanf.speedruncomapiapp.data.details.SpeedrunPlatform
import com.stepanf.speedruncomapiapp.data.details.SpeedrunRegion

data class SpeedrunPlatformResponse(
    @SerializedName("data") val platform: SpeedrunPlatform
)

data class SpeedrunGenreResponse(
    @SerializedName("data") val genre: SpeedrunGenre
)

data class SpeedrunDeveloperResponse(
    @SerializedName("data") val developer: SpeedrunDeveloper
)

data class SpeedrunRegionResponse(
    @SerializedName("data") val  region: SpeedrunRegion
)