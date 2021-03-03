package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

class TvShowViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> = movieAppRepository.getTvShows(sort)
}