package com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

class DetailTvShowViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<Resource<TvShowEntity>> = movieAppRepository.getDetailTvShow(tvShowId)
}