package com.rifki.jetpackpro.mymoviesfinal.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

class MovieViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> = movieAppRepository.getMovies(sort)
}