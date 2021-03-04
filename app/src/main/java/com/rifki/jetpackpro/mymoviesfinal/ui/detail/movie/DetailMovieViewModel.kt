package com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

class DetailMovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {
    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    var detailMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        movieAppRepository.getDetailMovie(mMovieId)
    }

    fun setFavoriteMovie() {
        val movieResource = detailMovie.value
        if (movieResource?.data != null) {
            val newState = !movieResource.data.isFavorite
            movieAppRepository.setFavoriteMovie(movieResource.data, newState)
        }
    }
}
