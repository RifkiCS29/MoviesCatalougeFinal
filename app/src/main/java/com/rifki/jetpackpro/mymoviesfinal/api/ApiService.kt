package com.rifki.jetpackpro.mymoviesfinal.api

import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.DetailMovieResponse
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.DetailTvShowResponse
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.MovieResponse
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/{tvShow_id}")
    fun getDetailTvShow(
        @Path("tvShow_id") tvShowId: String,
        @Query("api_key") apiKey: String
    ): Call<DetailTvShowResponse>
}