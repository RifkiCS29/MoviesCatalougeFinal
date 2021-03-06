package com.rifki.jetpackpro.mymoviesfinal.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieAppRepository: MovieAppRepository): ViewModel() {

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = movieAppRepository.getFavoriteTvShows()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavorite
        movieAppRepository.setFavoriteTvShow(tvShowEntity, newState)
    }
}