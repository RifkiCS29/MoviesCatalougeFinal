package com.rifki.jetpackpro.mymoviesfinal.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

interface MovieAppDataSource {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(movieId: String): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}