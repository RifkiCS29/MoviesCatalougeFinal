package com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mymoviesfinal.data.source.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity

class DetailMovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<DetailMovieEntity> = movieAppRepository.getDetailMovie(movieId)
}