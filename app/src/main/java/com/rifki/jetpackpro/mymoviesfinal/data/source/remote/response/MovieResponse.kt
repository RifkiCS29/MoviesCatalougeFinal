package com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("results")
	val results: List<ResultsMovieItem>
)

data class ResultsMovieItem(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: String
)
