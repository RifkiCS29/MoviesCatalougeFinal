package com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity (
        var id: String,
        var posterPath: String,
        var title: String,
        var voteAverage: Double
): Parcelable