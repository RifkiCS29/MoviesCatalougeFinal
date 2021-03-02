package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = movieAppRepository.getTvShows()
}