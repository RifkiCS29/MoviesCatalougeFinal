package com.rifki.jetpackpro.mymoviesfinal.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.di.Injection
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie.DetailMovieViewModel
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow.DetailTvShowViewModel
import com.rifki.jetpackpro.mymoviesfinal.ui.favorite.movie.FavoriteMovieViewModel
import com.rifki.jetpackpro.mymoviesfinal.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.rifki.jetpackpro.mymoviesfinal.ui.movie.MovieViewModel
import com.rifki.jetpackpro.mymoviesfinal.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val movieAppRepository: MovieAppRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(movieAppRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(movieAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(movieAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                return DetailTvShowViewModel(movieAppRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(movieAppRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                return FavoriteTvShowViewModel(movieAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}