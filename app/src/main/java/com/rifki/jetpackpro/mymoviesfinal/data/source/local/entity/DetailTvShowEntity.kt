package com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity

data class DetailTvShowEntity(
        val backdropPath: String,
        val genres: List<String>,
        val id: Int,
        val firstAirDate: String,
        val overview: String,
        val posterPath: String,
        val voteAverage: Double,
        val name: String,
        val tagline: String? = ""
)