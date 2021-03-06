package com.rifki.jetpackpro.mymoviesfinal.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = movieAppRepository.getFavoriteMovies()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        movieAppRepository.setFavoriteMovie(movieEntity, newState)
    }
}