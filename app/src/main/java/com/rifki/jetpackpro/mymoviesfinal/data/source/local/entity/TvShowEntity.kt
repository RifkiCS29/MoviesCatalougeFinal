package com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity (
        var id: String,
        var posterPath: String,
        var name: String,
        var voteAverage: Double
): Parcelable