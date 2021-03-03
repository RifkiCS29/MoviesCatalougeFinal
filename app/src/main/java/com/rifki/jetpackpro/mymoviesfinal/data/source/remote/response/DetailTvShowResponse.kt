package com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvShowResponse(

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("tagline")
	val tagline: String? = ""
)




