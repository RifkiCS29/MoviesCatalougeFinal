package com.rifki.jetpackpro.mymoviesfinal.data.source

import androidx.lifecycle.LiveData
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailTvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity

interface MovieAppDataSource {

    fun getMovies(): LiveData<List<MovieEntity>>

    fun getTvShows(): LiveData<List<TvShowEntity>>

    fun getDetailMovie(movieId: String): LiveData<DetailMovieEntity>

    fun getDetailTvShow(tvShowId: String): LiveData<DetailTvShowEntity>

}