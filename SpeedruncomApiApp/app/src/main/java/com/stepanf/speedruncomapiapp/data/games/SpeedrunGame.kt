package com.stepanf.speedruncomapiapp.data.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpeedrunGame(
    val id: String,
    val names: SpeedrunGameNames,
    val weblink: String,
    @SerializedName("release-date") val release_date: String,
    val platforms: List<String>,
    val regions: List<String>,
    val genres: List<String>,
    val developers: List<String>,
    val assets: SpeedrunGameAssets
) : Parcelable {
    @Parcelize
    data class SpeedrunGameNames(
        val international: String
    ) : Parcelable

    @Parcelize
    data class SpeedrunGameAssets(
        val icon: SpeedrunGameImage,
        @SerializedName("cover-medium") val cover_medium: SpeedrunGameImage
    ) : Parcelable {
        @Parcelize
        data class SpeedrunGameImage(val uri: String) : Parcelable
    }
}