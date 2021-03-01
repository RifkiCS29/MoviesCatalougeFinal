package com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("results")
	val results: List<ResultsTvShowItem>
)

data class ResultsTvShowItem(

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)
