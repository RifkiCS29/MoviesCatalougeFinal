package com.rifki.jetpackpro.mymoviesfinal.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mymoviesfinal.data.source.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity

class MovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieAppRepository.getMovies()
}